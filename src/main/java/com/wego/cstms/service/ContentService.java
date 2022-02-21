package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.CourseContentMapper;
import com.wego.cstms.exceptions.EntityType;
import com.wego.cstms.exceptions.MSException;
import com.wego.cstms.persistence.Entities.CourseEntity;
import com.wego.cstms.persistence.Entities.CourseContent;
import com.wego.cstms.persistence.repositories.CourseContentRepository;
import com.wego.cstms.persistence.repositories.CourseRepository;
import com.wego.cstms.dto.models.CourseContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    private final CourseContentRepository courseContentRepository;
    private final CourseRepository courseRepository;
    private final MSException msException;

    @Autowired
    public ContentService(CourseContentRepository courseContentRepository, CourseRepository courseRepository, MSException msException) {
        this.courseContentRepository = courseContentRepository;
        this.courseRepository = courseRepository;
        this.msException = msException;
    }

    public List<CourseContentDto> getCoursesAllContents(int id) {
        List<CourseContentDto> courseContents = new ArrayList<>();
        courseContentRepository.findByCourseId(id).forEach(courseContent -> {
            courseContents.add(CourseContentMapper.toCourseContentDto(courseContent));
        });
        return courseContents;
    }

    public String[] getDownloadPath(int courseId, int contentId){
        CourseContent courseContent = courseContentRepository.findById(contentId).get();
        String filename = courseContent.getFilename();
        String filepath = courseId+"/"+filename;
        return new String[]{filename, filepath};
    }


    public CourseContentDto addCourseContent(CourseContentDto courseContentDto, int courseId) {
        Optional<CourseEntity> course = courseRepository.findById(courseId);
        if (course.isPresent()){
            CourseContent courseContent = CourseContentMapper.toCourseContent(courseContentDto);
            courseContent.setCourse(course.get());
            courseContentRepository.save(courseContent);
            return courseContentDto;
        }
        throw msException.EntityNotFoundException(EntityType.COURSE,courseId);

    }
//
//    public void updateCourseContent(ContentDto contentDto) {
//
//        contentRepository.save(new Content(contentDto));
//    }
//
    public String deleteCourseContent(Integer courseId,Integer courseContentId) {
        if (courseRepository.existsById(courseId)){
            Optional<CourseContent> courseContent = courseContentRepository.findById(courseId);
            if(courseContent.isPresent()){
                courseContentRepository.deleteById(courseContentId);
                return String.format("Course Content:%s Deleted Successfully",courseContent.get().getFilename());
            }
            throw msException.EntityNotFoundException(EntityType.COURSE_CONTENT,courseId);
        }
        throw msException.EntityNotFoundException(EntityType.COURSE,courseId);

    }

    public void deleteAllCourseContent(Integer courseContentId) {
        courseContentRepository.deleteById(courseContentId);
    }



}
