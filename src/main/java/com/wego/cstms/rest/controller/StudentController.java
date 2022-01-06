package com.wego.cstms.rest.controller;

import com.wego.cstms.persistence.models.Student;
import com.wego.cstms.persistence.models.Teacher;
import com.wego.cstms.rest.models.StudentDto;
import com.wego.cstms.rest.models.TeacherDto;
import com.wego.cstms.service.StudentService;
import com.wego.cstms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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

}
