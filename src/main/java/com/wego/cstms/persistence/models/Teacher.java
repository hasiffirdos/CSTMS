package com.wego.cstms.persistence.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teaches",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")

    )
    private List<Course> taughtCourses = new ArrayList<>();

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