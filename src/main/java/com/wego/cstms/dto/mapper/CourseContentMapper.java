package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.CourseContentDto;
import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.CourseContent;
import com.wego.cstms.persistence.Entities.Student;

import java.util.Date;

public class CourseContentMapper {
    public static CourseContentDto toCourseContentDto(CourseContent courseContent){
        return new CourseContentDto()
                .setId(courseContent.getId())
                .setFileName(courseContent.getFilename())
                .setCreateAt(courseContent.getCreate_at())
                .setDescription(courseContent.getDescription())
                .setFileType(courseContent.getFiletype());
    }

    public static CourseContent toCourseContent(CourseContentDto courseContentDto){
        return new CourseContent()
                .setFilename(courseContentDto.getFileName())
                .setFiletype(courseContentDto.getFileType())
                .setDescription(courseContentDto.getDescription())
                .setCreate_at(new Date());
    }
}


