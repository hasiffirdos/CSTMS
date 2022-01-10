package com.wego.cstms.service;


import com.wego.cstms.persistence.models.Course;
import com.wego.cstms.persistence.models.CourseContent;
import com.wego.cstms.persistence.repositories.CourseContentRepository;
import com.wego.cstms.rest.models.CourseContentDto;
import com.wego.cstms.rest.models.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseContentService {

    private final CourseContentRepository courseContentRepository;

    @Autowired
    public CourseContentService(CourseContentRepository courseContentRepository) {
        this.courseContentRepository = courseContentRepository;
    }

    public List<CourseContent> getAllCourseContents(int courseId) {
        List<CourseContent> courseContents = new ArrayList<>();
        courseContentRepository.findAll().forEach(courseContents::add);
        return courseContents;
    }

    public CourseContent getCourseContent(Integer id) {
        return courseContentRepository.findById(id).get();
    }

    public void addCourseContent(CourseContentDto courseContentDto) {
        CourseContent courseContent = new CourseContent(courseContentDto);
        courseContentRepository.save(courseContent);
    }

    public void updateCourseContent(CourseContentDto courseContentDto) {

        courseContentRepository.save(new CourseContent(courseContentDto));
    }

    public void deleteCourseContent(Integer courseContentId) {
        courseContentRepository.deleteById(courseContentId);
    }


}
