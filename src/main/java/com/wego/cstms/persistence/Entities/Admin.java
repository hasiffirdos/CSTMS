package com.wego.cstms.persistence.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wego.cstms.rest.models.AdminDto;
import com.wego.cstms.rest.models.TeacherDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private Date   dob;
    private Date   signupDate;
    private int    age;

    public Admin(AdminDto adminDto) {
        this.firstname  = adminDto.getFirstname();
        this.lastname   = adminDto.getLastname();
        this.email      = adminDto.getEmail();
        this.password   = adminDto.getPassword();
        this.phone      = adminDto.getPhone();
        this.dob        = adminDto.getDob();
        this.signupDate = new Date();
        this.age        = adminDto.getAge();
    }
}