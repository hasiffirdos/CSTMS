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
@Table( name = "teachers")
public class TeacherEntity extends UserEntity {
//    @Id
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
            name = "teaches",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")

    )
    private List<CourseEntity> taughtCourses = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private User user;

}