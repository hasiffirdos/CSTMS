package com.wego.cstms.persistence.models;

import com.wego.cstms.rest.models.StudentDto;
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
@Table(name = "students")
public class Student {
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

    public Student(StudentDto studentDto) {
        this.firstname  = studentDto.getFirstname();
        this.lastname   = studentDto.getLastname();
        this.email      = studentDto.getEmail();
        this.password   = studentDto.getPassword();
        this.phone      = studentDto.getPhone();
        this.dob        = studentDto.getDob();
        this.signupDate = new Date();
        this.age        = studentDto.getAge();
    }
}