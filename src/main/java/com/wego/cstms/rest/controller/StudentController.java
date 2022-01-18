package com.wego.cstms.rest.controller;

import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.Student;
import com.wego.cstms.persistence.Entities.User;
import com.wego.cstms.rest.models.CourseDto;
import com.wego.cstms.rest.models.StudentDto;
import com.wego.cstms.security.base.security.ApplicationUserRole;
import com.wego.cstms.service.CourseService;
import com.wego.cstms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;


    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }
//    OPEN
    @RequestMapping("/students")
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

//    OPEN
    @RequestMapping(value = "/students/{id}")
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
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public void deactivateStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/courses/{courseId}/students/{studentId}/enroll-course")
    @RequestMapping(method = RequestMethod.POST, value = "/students/{studentId}/courses/{courseId}/enroll")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public void enrollCourse(@PathVariable int studentId, @PathVariable int courseId){
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);
        student.getEnrolledCourses().add(course);
        studentService.updateStudent(student);
//        course.addStudent(student);
//        courseService.updateCourse(course);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/students/{studentId}/courses")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public List<Course> getEnrolledCourses(@PathVariable int studentId){
        return studentService.getEnrolledCourses(studentId);
    }

}
