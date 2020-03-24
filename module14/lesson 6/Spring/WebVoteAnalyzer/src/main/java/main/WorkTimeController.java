package main;

import main.model.Voter;
import main.model.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import response.WorkTime;

import java.util.List;

@RestController
public class WorkTimeController {

    @Autowired
    private VoterRepository voterRepository;

    @PostMapping("/voters")
    public int add (Voter voter){
       Voter newVoter =  voterRepository.save(voter);
       return newVoter.getId();
    }

    @GetMapping("/times")
    public List<WorkTime> list () {
        return Handler.getWorkTimes();
    }
    @GetMapping("/times/{id}")
    public ResponseEntity station(@PathVariable int id){
        WorkTime wt = Handler.getOneWorkTime(id);
        if(wt==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(wt, HttpStatus.OK);
    }
}
