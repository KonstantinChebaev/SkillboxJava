package ru.skillbox.loanrequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeafaultController {
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("applicant", new Applicant());
        return "index";
    }
    @PostMapping("/")
    public String submit(@ModelAttribute Applicant applicant) {
        return "result";
    }


}
