package ru.skillbox.loanrequest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
@JsonAutoDetect
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private int id;
    private String gender;
    @NotNull
    @Size(min=2, max=30)
    private String surname;
    @NotNull
    @Size(min=2, max=30)
    private String name;
    private String lastName;
    @NotNull
    private String bDay;
    @NotNull
    private int income;
    private String attPony;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String tel;
    @NotNull
    @Size(min=4, max=4 )
    private String pasSer;
    @NotNull
    @Size(min=6, max=6 )
    private String pasNum;
    @NotNull
    private String pasDay;
    @NotNull
    private String pasKem;
    @NotNull
    @Size(min=7, max=7 )
    private String pasKod;


    public String trullyGetCheck (){
        if (attPony.equals("Не очень")){
            return "Своим отношением вы только расстроите наших сотрудников. Обратитесь в другой банк.";
        } else if (attPony.equals("Я - Сумеречная Искорка")){
            return "Мы с радостью одобрим Ваш кредит. Наш сотрудник свяжется с Вами в течение 14-23 секунд.";
        }
        LocalDate bDate = LocalDate.parse(bDay);
        LocalDate currDate = LocalDate.now();
        if (Period.between(bDate,currDate).getYears()<18){
            return "Приходите когда Вам исполнится 18";
        }
        if (income<10_000){
            return "К сожалению, мы не готовы рисковать с вашим уровнем дохода.";
        }
        return "Первичная проверка заявки на кредит пройдена. Наш сотрудник свяжется с Вами в течение трех рабочих дней.";
    }


}
