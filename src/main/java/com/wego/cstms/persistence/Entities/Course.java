package com.wego.cstms.persistence.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wego.cstms.dto.models.CourseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Accessors(chain = true)
@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private int price;
    private float rating;
    private String description;
    private int prerequisite;
    private int views;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledCourses")
    private List<StudentEntity> registeredStudents = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "taughtCourses")
    private List<Teacher> teachers = new ArrayList<>();



}

