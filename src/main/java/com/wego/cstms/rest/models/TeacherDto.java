package com.wego.cstms.rest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
public class TeacherDto {
//    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String role;
    private Date signupDate;
    private int age;

}
