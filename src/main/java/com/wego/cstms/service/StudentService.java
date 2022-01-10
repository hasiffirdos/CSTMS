package com.wego.cstms.service;


import com.wego.cstms.persistence.models.Course;
import com.wego.cstms.persistence.models.Student;
import com.wego.cstms.persistence.repositories.StudentRepository;
import com.wego.cstms.rest.models.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    public Student getStudent(Integer id) {
        return studentRepository.findById(id).get();
    }

    public void addStudent(StudentDto studentDto) {

        Student student = new Student(studentDto);
        studentRepository.save(student);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public List<Course> getEnrolledCourses(int studentId) {
        return studentRepository.findById(studentId).get().getEnrolledCourses();
    }
}
