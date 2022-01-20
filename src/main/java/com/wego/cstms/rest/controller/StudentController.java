package com.wego.cstms.rest.controller;

import com.wego.cstms.dto.models.CourseContentDto;
import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.Student;
import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.service.ContentService;
import com.wego.cstms.service.CourseService;
import com.wego.cstms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final ContentService contentService;


    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, ContentService contentService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.contentService = contentService;
    }
//    OPEN
    @RequestMapping("/students")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<StudentDto> getStudents() {
        return studentService.getAllStudents();
    }

//    OPEN
    @RequestMapping(value = "/students/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or @principalSecurity.hasUserId(authentication,#id)")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getStudent(id);
    }


//    TODO: only Admin should call this.
    @RequestMapping(method = RequestMethod.POST, value = "/students")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void registerStudent(@RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/students/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT') or @principalSecurity.hasUserId(authentication,#id)")
    public void deactivateStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/courses/{courseId}/students/{studentId}/enroll-course")
    @RequestMapping(method = RequestMethod.POST, value = "/students/{studentId}/courses/{courseId}/enroll")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')  or @principalSecurity.hasUserId(authentication,#studentId)")
    public void enrollCourse(@PathVariable int studentId, @PathVariable int courseId){
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);
        student.getEnrolledCourses().add(course);
        studentService.updateStudent(student);
//        course.addStudent(student);
//        courseService.updateCourse(course);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/students/{studentId}/courses")
    @PreAuthorize("hasAnyRole('ADMIN') or @principalSecurity.hasUserId(authentication,#studentId)")
    public List<Course> getEnrolledCourses(@PathVariable int studentId){
        return studentService.getEnrolledCourses(studentId);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/students/{studentId}/courses/{courseId}/course-content")
    @PreAuthorize("hasAnyRole('ADMIN') or @principalSecurity.hasEnrolledCourse(authentication,#studentId,#courseId)")
    public List<CourseContentDto> getEnrolledCoursesContent(@PathVariable int studentId, @PathVariable int courseId){
        return contentService.getCoursesAllContents(studentId);
    }

}
