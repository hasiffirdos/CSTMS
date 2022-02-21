package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.CourseContentMapper;
import com.wego.cstms.dto.mapper.CourseMapper;
import com.wego.cstms.exceptions.EntityType;
import com.wego.cstms.exceptions.MSException;
import com.wego.cstms.persistence.Entities.CourseEntity;
import com.wego.cstms.persistence.Entities.CourseContentEntity;
import com.wego.cstms.persistence.repositories.CourseContentRepository;
import com.wego.cstms.dto.models.CourseContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    private final CourseContentRepository courseContentRepository;
    private final CourseService courseService;
    private final MSException msException;

    @Autowired
    public ContentService(CourseContentRepository courseContentRepository, CourseService courseService, MSException msException) {
        this.courseContentRepository = courseContentRepository;
        this.courseService = courseService;
        this.msException = msException;
    }

    public List<CourseContentDto> getCoursesAllContents(int id) {
        List<CourseContentDto> courseContents = new ArrayList<>();
        courseContentRepository.findByCourseId(id).forEach(courseContentEntity -> {
            courseContents.add(CourseContentMapper.toCourseContentDto(courseContentEntity));
        });
        return courseContents;
    }

    public String[] getDownloadPath(int courseId, int contentId){
        CourseContentEntity courseContentEntity = courseContentRepository.findById(contentId).get();
        String filename = courseContentEntity.getFilename();
        String filepath = courseId+"/"+filename;
        return new String[]{filename, filepath};
    }


    public CourseContentDto addCourseContent(CourseContentDto courseContentDto, int courseId) {
        CourseEntity courseEntity = CourseMapper.toCourse(courseService.getCourse(courseId));
        if (courseEntity!=null){
            CourseContentEntity courseContentEntity = CourseContentMapper.toCourseContent(courseContentDto);
            courseContentEntity.setCourse(courseEntity);
            courseContentRepository.save(courseContentEntity);
            return courseContentDto;
        }
        throw msException.EntityNotFoundException(EntityType.COURSE,courseId);

    }
    public String deleteCourseContent(Integer courseId,Integer courseContentId) {
        if (courseService.courseExists(courseId)){
            Optional<CourseContentEntity> courseContent = courseContentRepository.findById(courseId);
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
