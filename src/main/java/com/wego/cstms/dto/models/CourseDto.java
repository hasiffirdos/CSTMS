package com.wego.cstms.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Setter
@Getter
public class CourseDto {

    private int id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private String description;
    private int price;
    private float rating;
    private int prerequisite;
    private int views;
}

