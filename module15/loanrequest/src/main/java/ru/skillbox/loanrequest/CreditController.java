package ru.skillbox.loanrequest;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.Period;

@Controller
public class CreditController {

    @GetMapping("/credit/")
    public String showForm(Model model) {
        model.addAttribute("applicant", new Applicant());
        return "credit";
    }
    @PostMapping("/credit/")
    public String submit(@ModelAttribute Applicant applicant, Model model) {
        String itog = check(applicant);
        model.addAttribute("itog", itog);
        return "result";
    }
    private static String check (Applicant a){
        String ponyLike = a.getAttPony();
        if (ponyLike.equals("Не очень")){
            return "Своим отношением вы только расстроите наших сотрудников. Обратитесь в другой банк.";
        } else if (ponyLike.equals("Я - Сумеречная Искорка")){
            return "Мы с радостью одобрим Ваш кредит. Наш сотрудник свяжется с Вами в течение 14-23 секунд.";
        }
        LocalDate bDate = LocalDate.parse(a.getBDay());
        LocalDate currDate = LocalDate.now();
        if (Period.between(bDate,currDate).getYears()<18){
            return "Приходите когда Вам исполнится 18";
        }
        int income = a.getIncome();
        if (income<10_000){
            return "К сожалению, мы не готовы рисковать с вашим уровнем дохода.";
        }
        return "Первичная проверка заявки на кредит пройдена. Наш сотрудник свяжется с Вами в течение трех рабочих дней.";
    }
}
