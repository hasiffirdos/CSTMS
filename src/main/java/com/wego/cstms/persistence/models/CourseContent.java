package com.wego.cstms.persistence.models;

import com.wego.cstms.persistence.repositories.CourseContentRepository;
import com.wego.cstms.rest.models.CourseContentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String filename;
    private String fileType;
    private Date createDate;
    private String description;

    @ManyToOne
    private Course course;

    public CourseContent(CourseContentDto courseContentDto) {
        this.filename = courseContentDto.getFilename();
        this.fileType = courseContentDto.getFileType();
        this.createDate = courseContentDto.getCreateDate();
        this.description = courseContentDto.getDescription();
    }
}
