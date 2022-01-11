package com.wego.cstms.rest.controller;

import com.wego.cstms.persistence.models.Course;
import com.wego.cstms.persistence.models.Student;
import com.wego.cstms.rest.models.CourseDto;
import com.wego.cstms.rest.models.StudentDto;
import com.wego.cstms.service.CourseService;
import com.wego.cstms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;


    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @RequestMapping("/students")
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "/students/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getStudent(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/students")
    public void registerStudent(@RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/students/{id}")
    public void deactivateStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/courses/{courseId}/students/{studentId}/enroll-course")
    public void enrollCourse(@RequestBody CourseDto courseDto, @PathVariable int studentId, @PathVariable int courseId){
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);
        student.getEnrolledCourses().add(course);
        studentService.updateStudent(student);
//        course.addStudent(student);
//        courseService.updateCourse(course);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/students/{studentId}/get-enrolled-courses")
    public List<Course> getEnrolledCourses(@PathVariable int studentId){
        return studentService.getEnrolledCourses(studentId);
    }

}