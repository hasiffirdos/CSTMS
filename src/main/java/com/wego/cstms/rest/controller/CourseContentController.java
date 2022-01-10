package com.wego.cstms.rest.controller;

import com.wego.cstms.persistence.models.Course;
import com.wego.cstms.persistence.models.CourseContent;
import com.wego.cstms.persistence.models.Student;
import com.wego.cstms.rest.models.CourseContentDto;
import com.wego.cstms.rest.models.CourseDto;
import com.wego.cstms.service.CourseContentService;
import com.wego.cstms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseContentController {

    private final CourseContentService courseContentService;

    @Autowired
    public CourseContentController(CourseContentService courseContentService) {
        this.courseContentService = courseContentService;
    }

    @RequestMapping("/courses/{courseId}/course-contents")
    public List<CourseContent> getCoursesContent(@PathVariable int courseId){
        return courseContentService.getAllCourseContents(courseId);
    }


//    TODO: do change this with uploading file functionality.
//    uploading course content
    @RequestMapping( method = RequestMethod.POST, value = "/courses/{courseId}/course-contents")
    public void addCourseContent(@RequestBody CourseContentDto courseContentDto,
                                 @PathVariable Integer courseId){
        courseContentService.addCourseContent(courseContentDto);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/courses/{courseId}/course-contents/{courseContentId}")
    public void deleteCourse(@PathVariable Integer courseId, @PathVariable int courseContentId){
        courseContentService.deleteCourseContent(courseContentId);
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/courses/{courseId}/course-content/")
//    public List<Student> updateCourseContent(@PathVariable int courseId){
//        return courseContentService.updateCourseContent(courseId);
//    }




}
