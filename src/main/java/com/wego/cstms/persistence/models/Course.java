package com.wego.cstms.persistence.models;

import com.wego.cstms.rest.models.CourseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
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


    public Course(CourseDto courseDto) {
        this.name = courseDto.getName();
        this.createdAt = courseDto.getCreatedAt();
        this.updatedAt = courseDto.getUpdatedAt();
        this.price = courseDto.getPrice();
        this.rating = courseDto.getRating();
        this.description = courseDto.getDescription();
        this.prerequisite = courseDto.getPrerequisite();
        this.views = courseDto.getViews();
    }
}

