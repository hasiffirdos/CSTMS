package com.wego.cstms.rest.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class CourseContentDto {

    private String filename;
    private String fileType;
    private Date createDate;
    private String description;

}

