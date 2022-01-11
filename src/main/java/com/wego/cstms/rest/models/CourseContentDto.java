package com.wego.cstms.rest.models;

import com.wego.cstms.persistence.models.Course;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import java.util.Date;


@Setter
@Getter
public class CourseContentDto {

    private String fileName;
    private String fileType;
    private Date createAt;
    private String description;


    @ManyToOne
    private Course course;

}

