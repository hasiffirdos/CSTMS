package com.wego.cstms.persistence.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Accessors(chain = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student extends User{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int    id;
//    private String firstname;
//    private String lastname;
//    private String email;
////    private String password;
//    private String phone;
//    private Date   dob;
//    private Date   signupDate;
//    private int    age;
    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "enrollments",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")

    )
    private List<Course> enrolledCourses = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private User user;

}