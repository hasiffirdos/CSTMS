package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.CourseDto;
import com.wego.cstms.persistence.Entities.CourseEntity;

import java.util.Date;

public class CourseMapper {
    public static CourseDto toCourseDto(CourseEntity courseEntity){
        return new CourseDto()
                .setId(courseEntity.getId())
                .setName(courseEntity.getName())
                .setCreatedAt(courseEntity.getCreatedAt())
                .setPrice(courseEntity.getPrice())
                .setDescription(courseEntity.getDescription())
                .setRating(courseEntity.getRating())
                .setPrerequisite(courseEntity.getPrerequisite())
                .setViews(courseEntity.getViews());
    }

    public static CourseEntity toCourse(CourseDto courseDto){
        return new CourseEntity()
                .setName(courseDto.getName())
                .setCreatedAt(new Date())
                .setUpdatedAt(new Date())
                .setPrerequisite(courseDto.getPrerequisite())
                .setPrice(courseDto.getPrice())
                .setDescription(courseDto.getDescription())
//                these should be initialized with zero, passing it for testing purposes
                .setViews(courseDto.getViews())
                .setRating(courseDto.getRating());

    }
}