package com.wego.cstms.persistence.models;

import com.wego.cstms.rest.models.TeacherDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "teachers")
public class Teacher {
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

    public Teacher(TeacherDto teacherDto) {
        this.firstname  = teacherDto.getFirstname();
        this.lastname   = teacherDto.getLastname();
        this.email      = teacherDto.getEmail();
        this.password   = teacherDto.getPassword();
        this.phone      = teacherDto.getPhone();
        this.dob        = teacherDto.getDob();
        this.signupDate = new Date();
        this.age        = teacherDto.getAge();
    }
}