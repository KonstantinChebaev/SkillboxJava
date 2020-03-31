package ru.skillbox.loanrequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeafaultController {
    @RequestMapping("/")
    public String index (Model model) throws Exception {
        return "index";
    }

}
