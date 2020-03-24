package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @RequestMapping("/")
    public String index (){
        return "<a href=\"http://localhost:8080/index.html\">Посмотреть на время работы станций</a>";
    }
}
