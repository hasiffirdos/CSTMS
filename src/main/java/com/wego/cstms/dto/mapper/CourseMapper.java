package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.CourseDto;
import com.wego.cstms.persistence.Entities.Course;

import java.util.Date;

public class CourseMapper {
    public static CourseDto toCourseDto(Course course){
        return new CourseDto()
                .setId(course.getId())
                .setName(course.getName())
                .setCreatedAt(course.getCreatedAt())
                .setPrice(course.getPrice())
                .setDescription(course.getDescription())
                .setRating(course.getRating())
                .setPrerequisite(course.getPrerequisite())
                .setViews(course.getViews());
    }

    public static Course toCourse(CourseDto courseDto){
        return new Course()
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