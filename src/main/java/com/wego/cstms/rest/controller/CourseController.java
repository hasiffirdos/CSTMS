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
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    @Autowired
    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }
//    OPEN
    @RequestMapping("/courses")
    public List<Course> getCourses(){
        return courseService.getAllCourses();
    }
//    OPEN
    @RequestMapping("/courses/top-{numberOfCourses}-all-times")
    public List<Course> getCoursesTopAllTimes(@PathVariable int numberOfCourses){
        return courseService.getTopAllTimes(numberOfCourses);
    }
//    OPEN
    @RequestMapping("/courses/top-{numberOfCourses}-trending")
    public List<Course> getCoursesTopTrending(@PathVariable int numberOfCourses){
        return courseService.getTopTrending(numberOfCourses);
    }


//    OPEN
    @RequestMapping( value = "/courses/{id}")
    public Course getCourse(@PathVariable Integer id){
        return courseService.getCourse(id);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/courses/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public void deleteCourse(@PathVariable Integer id){
        courseService.deleteCourse(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/courses/{courseId}/students")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public List<Student> getRegisteredStudents(@PathVariable int courseId){
        return courseService.getRegisteredStudents(courseId);
    }




}
