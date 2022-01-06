package com.wego.cstms.rest.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Setter
@Getter
public class CourseDto {

    private String name;
    private Date createdAt;
    private Date updatedAt;
    private String description;
    private int price;
    private float rating;
    private int prerequisite;
    private int views;
/*
    uncomment it when you have done with adding CourseContent Model
    @OneToMany
    private List<CourseContent> contentId;
*/
}

