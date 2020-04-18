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
    public String showForm(Model model, HttpServletRequest request, HttpSession session) throws IOException {
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        if(applicant==null)applicant = this.buildApplicant();
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie c : cookies){
//                if (c.getName().equals("applicant")){
//                    String value = URLDecoder.decode(c.getValue(), "UTF-8");
//                    StringReader reader = new StringReader(value);
//                    ObjectMapper mapper = new ObjectMapper();
//                    applicant = mapper.readValue(reader, Applicant.class);
//                }
//            }
//        }
        model.addAttribute("applicant", applicant);
        return "credit";
    }
    @PostMapping("/credit/")
    public String submit(@ModelAttribute Applicant applicant, Model model, HttpServletResponse response, HttpSession session) throws IOException {
//        StringWriter writer = new StringWriter();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(writer, applicant);
//        String appString = writer.toString();
//        Cookie cookie = new Cookie("applicant", URLEncoder.encode(appString, "UTF-8"));
//        response.addCookie(cookie);
        session.setAttribute("applicant", applicant);
        String itog = applicant.trullyGetCheck();
        model.addAttribute("itog", itog);
        return "result";
    }

    private Applicant buildApplicant (){
        Applicant applicant = new Applicant();
        applicant.setAttPony("Не очень");
        applicant.setBDay("2000-10-10");
        applicant.setEmail("example@mail.com");
        applicant.setGender("Ж");
        applicant.setIncome(1);
        applicant.setLastName("Фамилия");
        applicant.setName("Имя");
        applicant.setPasDay("2000-10-10");
        applicant.setPasKem("Отделением №");
        applicant.setPasKod("111-111");
        applicant.setPasNum("000000");
        applicant.setPasSer("0000");
        applicant.setSurname("Отчество");
        applicant.setTel("88005553535");
        return applicant;

    }

}
