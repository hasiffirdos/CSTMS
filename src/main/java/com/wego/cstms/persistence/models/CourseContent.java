package com.wego.cstms.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wego.cstms.rest.models.CourseContentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course_contents")
public class CourseContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String filename;
    private String filetype;
    private Date create_at;
    private String description;

    @JsonIgnore
    @ManyToOne
    private Course course;


    public CourseContent(CourseContentDto courseContentDto) {

        this.filename = courseContentDto.getFileName();
        this.filetype = courseContentDto.getFileType();
        this.create_at = new Date();
        this.description = courseContentDto.getDescription();
    }
}

