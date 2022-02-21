package com.wego.cstms.rest.controller;

import com.wego.cstms.dto.response.CustomResponse;
import com.wego.cstms.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @RequestMapping("")
    public ResponseEntity<CustomResponse> getCourses(){
        return ResponseEntity.ok(CustomResponse.builder().payLoad(courseService.getAllCourses()).build());
    }
    @RequestMapping("/top-{numberOfCourses}-all-times")
    public ResponseEntity<CustomResponse> getCoursesTopAllTimes(@PathVariable int numberOfCourses){
        return ResponseEntity.ok(CustomResponse.builder().payLoad(courseService.getTopAllTimes(numberOfCourses)).build());
    }
//    OPEN
    @RequestMapping("/top-{numberOfCourses}-trending")
    public ResponseEntity<CustomResponse> getCoursesTopTrending(@PathVariable int numberOfCourses){
        return ResponseEntity.ok(CustomResponse.builder().payLoad(courseService.getTopTrending(numberOfCourses)).build());
    }


//    OPEN
    @RequestMapping( value = "/{courseId}")
    public ResponseEntity<CustomResponse> getCourse(@PathVariable Integer courseId){

        try{
            return ResponseEntity.ok(CustomResponse.builder().payLoad(courseService.getCourse(courseId)).build());
        }catch (RuntimeException exception){
            return ResponseEntity.badRequest().body(CustomResponse.builder().errorMessage(exception.getMessage()).build());
        }

    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{courseId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomResponse> deleteCourse(@PathVariable Integer courseId){
        try{
            return ResponseEntity.ok(CustomResponse.builder().payLoad(courseService.deleteCourse(courseId)).build());
        }catch (RuntimeException exception){
            return ResponseEntity.badRequest().body(CustomResponse.builder().errorMessage(exception.getMessage()).build());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{courseId}/students")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.isCourseOwner(authentication,#courseId))")
    public ResponseEntity<CustomResponse> getRegisteredStudents(@PathVariable int courseId){
        try{
            return ResponseEntity.ok(CustomResponse.builder().payLoad(courseService.getRegisteredStudents(courseId)).build());
        }catch (RuntimeException exception){
            return ResponseEntity.badRequest().body(CustomResponse.builder().errorMessage(exception.getMessage()).build());
        }
    }




}
