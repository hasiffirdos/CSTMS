package com.wego.cstms.dto.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;


@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private Date dob;
    private int age;

}
