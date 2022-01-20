package com.wego.cstms.dto.models;

import com.wego.cstms.persistence.Entities.Course;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.ManyToOne;
import java.util.Date;

@Accessors(chain = true)
@Setter
@Getter
public class CourseContentDto {

    private int id;
    private String fileName;
    private String fileType;
    private Date createAt;
    private String description;


    @ManyToOne
    private Course course;

}

