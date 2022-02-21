package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.CourseContentDto;
import com.wego.cstms.persistence.Entities.CourseContentEntity;

import java.util.Date;

public class CourseContentMapper {
    public static CourseContentDto toCourseContentDto(CourseContentEntity courseContentEntity){
        return new CourseContentDto()
                .setId(courseContentEntity.getId())
                .setFileName(courseContentEntity.getFilename())
                .setCreateAt(courseContentEntity.getCreate_at())
                .setDescription(courseContentEntity.getDescription())
                .setFileType(courseContentEntity.getFiletype());
    }

    public static CourseContentEntity toCourseContent(CourseContentDto courseContentDto){
        return new CourseContentEntity()
                .setFilename(courseContentDto.getFileName())
                .setFiletype(courseContentDto.getFileType())
                .setDescription(courseContentDto.getDescription())
                .setCreate_at(new Date());
    }
}


