package com.wego.cstms.service;


import com.wego.cstms.persistence.models.CourseContent;
import com.wego.cstms.persistence.repositories.CourseContentRepository;
import com.wego.cstms.persistence.repositories.CourseRepository;
import com.wego.cstms.rest.models.CourseContentDto;
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

    public List<CourseContent> getCoursesAllContents(int id) {
        List<CourseContent> courseContent = new ArrayList<>();
        courseContentRepository.findByCourseId(id).forEach(courseContent::add);
        return courseContent;
    }


    public void addCourseContent(CourseContentDto courseContentDto, int courseId) {

        CourseContent courseContent = new CourseContent(courseContentDto);
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


}
