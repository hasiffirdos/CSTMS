package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.CourseContentMapper;
import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.CourseContent;
import com.wego.cstms.persistence.repositories.CourseContentRepository;
import com.wego.cstms.persistence.repositories.CourseRepository;
import com.wego.cstms.dto.models.CourseContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentService {

    private final CourseContentRepository courseContentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public ContentService(CourseContentRepository courseContentRepository, CourseRepository courseRepository) {
        this.courseContentRepository = courseContentRepository;
        this.courseRepository = courseRepository;
    }

    public List<CourseContentDto> getCoursesAllContents(int id) {
        List<CourseContentDto> courseContents = new ArrayList<>();
        courseContentRepository.findByCourseId(id).forEach(courseContent -> {
            courseContents.add(CourseContentMapper.toCourseContentDto(courseContent));
        });
        return courseContents;
    }


    public void addCourseContent(CourseContentDto courseContentDto, int courseId) {

        CourseContent courseContent = CourseContentMapper.toCourseContent(courseContentDto);
        courseContent.setCourse(courseRepository.findById(courseId).get());
        courseContentRepository.save(courseContent);
    }
//
//    public void updateCourseContent(ContentDto contentDto) {
//
//        contentRepository.save(new Content(contentDto));
//    }
//
    public void deleteCourseContent(Integer courseContentId) {
        courseContentRepository.deleteById(courseContentId);
    }

    public void deleteAllCourseContent(Integer courseContentId) {
        courseContentRepository.deleteById(courseContentId);
    }


}
