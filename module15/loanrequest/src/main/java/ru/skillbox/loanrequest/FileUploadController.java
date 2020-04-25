package ru.skillbox.loanrequest;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.skillbox.loanrequest.storage.StorageException;
import ru.skillbox.loanrequest.storage.StorageService;
import ru.skillbox.loanrequest.visits.BVRepository;
import ru.skillbox.loanrequest.visits.BrowserVisits;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileUploadController {
    private final StorageService storageService;
    @Autowired
    private BVRepository bvRepository;
    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    @GetMapping("/")
    public String listUploadedFiles(Model model, HttpServletRequest request) throws IOException {
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        if(!bvRepository.existsById(1)){
            bvRepository.save(new BrowserVisits(1,"opera",0));
            bvRepository.save(new BrowserVisits(2,"chrome",0));
            bvRepository.save(new BrowserVisits(3,"firefox",0));
        }
        this.makeVisit(request, model);
        return "index";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/";
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageException e) {
        return ResponseEntity.notFound().build();
    }

    private void makeVisit (HttpServletRequest request, Model model){
        String agent = request.getHeader("user-agent");
        BrowserVisits bv;
        if(agent.contains("OPR")){
            bv = bvRepository.findById(1).get();
        } else if (agent.contains("Firefox")){
            bv = bvRepository.findById(3).get();
        } else {
            bv = bvRepository.findById(2).get();
        }
        bv.setVisits(bv.getVisits()+1);
        bvRepository.save(bv);
        Iterable<BrowserVisits> allvisits = bvRepository.findAll();
        for(BrowserVisits br : allvisits){
            if(br.getBrowser().equals("opera")){
                model.addAttribute("opera",br.getVisits());
            }
            if(br.getBrowser().equals("chrome")){
                model.addAttribute("chrome",br.getVisits());
            }
            if(br.getBrowser().equals("firefox")){
                model.addAttribute("firefox",br.getVisits());
            }
        }
    }



}


// opera   Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.113 Safari/537.36 OPR/68.0.3618.52
// chrome   Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36
// firefox   Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0