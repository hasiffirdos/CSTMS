package com.wego.cstms.rest.controller;

import com.wego.cstms.persistence.models.Course;
import com.wego.cstms.persistence.models.Student;
import com.wego.cstms.rest.models.CourseDto;
import com.wego.cstms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/courses")
    public List<Course> getCourses(){
        return courseService.getAllCourses();
    }

    @RequestMapping("/courses/top-{numberOfCourses}-all-times")
    public List<Course> getCoursesTopAllTimes(@PathVariable int numberOfCourses){
        return courseService.getTopAllTimes(numberOfCourses);
    }

    @RequestMapping("/courses/top-{numberOfCourses}-trending")
    public List<Course> getCoursesTopTrending(@PathVariable int numberOfCourses){
        return courseService.getTopTrending(numberOfCourses);
    }

    @RequestMapping( value = "/courses/{id}")
    public Course getCourse(@PathVariable Integer id){
        return courseService.getCourse(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/courses")
    public void addCourse(@RequestBody CourseDto courseDto){
        courseService.addCourse(courseDto);
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/courses/{courseId}/students/{studentId}/")
//    public void enrollCourse(@RequestBody CourseDto courseDto, @PathVariable int studentId, @PathVariable int courseId){
//        Student std = new Student();
//        std.setId(studentId);
//        Course course = new Course();
//        course.setId(courseId);
//        std.enrollCourse(course);
////        Student student = new Student();
////        student.setEnrolledCourses(new Sou);
//        studentRepository.addCourse(courseDto);
//    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/courses/{id}")
    public void deleteCourse(@PathVariable Integer id){
        courseService.deleteCourse(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/courses/{courseId}/registered-students")
    public List<Student> getRegisteredStudents(@PathVariable int courseId){
        return courseService.getRegisteredStudents(courseId);
    }




}
