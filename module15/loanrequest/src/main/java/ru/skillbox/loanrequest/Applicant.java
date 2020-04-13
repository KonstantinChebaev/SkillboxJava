package ru.skillbox.loanrequest;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Applicant {
    @Id
    @GeneratedValue
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


}
