package ru.skillbox.loanrequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Controller
public class CreditController {

    @GetMapping("/credit/")
    public String showForm(Model model, HttpSession session) {
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        if (applicant == null) {
            applicant = this.buildApplicant();
        }
        model.addAttribute("applicant", applicant);
        return "credit";
    }


    @PostMapping("/credit/")
    public String submit(@ModelAttribute Applicant applicant, Model model, HttpSession session) {
        session.setAttribute("applicant", applicant);
        String itog = applicant.trullyGetCheck();
        model.addAttribute("itog", itog);
        return "result";
    }

    private Applicant buildApplicant() {
        return Applicant.builder()
                .attPony("Не очень")
                .bDay("2000-10-10")
                .email("example@mail.com")
                .gender("Ж")
                .income(1)
                .lastName("Фамилия")
                .name("Имя")
                .pasDay("2000-10-10")
                .pasKem("Отделением №")
                .pasKod("111-111")
                .pasNum("000000")
                .pasSer("0000")
                .surname("Отчество")
                .tel("88005553535")
                .build();
    }
}
