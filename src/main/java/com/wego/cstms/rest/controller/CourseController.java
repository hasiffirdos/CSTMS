package com.wego.cstms.rest.controller;

import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.Student;
import com.wego.cstms.service.CourseService;
import com.wego.cstms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
//    @Autowired
    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }
//    OPEN
    @RequestMapping("")
    public List<Course> getCourses(){
        return courseService.getAllCourses();
    }
//    OPEN
    @RequestMapping("/top-{numberOfCourses}-all-times")
    public List<Course> getCoursesTopAllTimes(@PathVariable int numberOfCourses){
        return courseService.getTopAllTimes(numberOfCourses);
    }
//    OPEN
    @RequestMapping("/top-{numberOfCourses}-trending")
    public List<Course> getCoursesTopTrending(@PathVariable int numberOfCourses){
        return courseService.getTopTrending(numberOfCourses);
    }


//    OPEN
    @RequestMapping( value = "/{courseId}")
    public Course getCourse(@PathVariable Integer courseId){
        return courseService.getCourse(courseId);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{courseId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCourse(@PathVariable Integer courseId){
        courseService.deleteCourse(courseId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{courseId}/students")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.isCourseOwner(authentication,#courseId))")
    public List<Student> getRegisteredStudents(@PathVariable int courseId){
        return courseService.getRegisteredStudents(courseId);
    }




}
