package com.wego.cstms.persistence.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wego.cstms.rest.models.StudentDto;
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
    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "enrollments",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")

    )
    private List<Course> enrolledCourses = new ArrayList<>();

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

//    public void enrollCourse(Course course){
//        this.enrolledCourses.add(course);
//    }
}