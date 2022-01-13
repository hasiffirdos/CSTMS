package com.wego.cstms.service;


import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.Student;
import com.wego.cstms.persistence.Entities.User;
import com.wego.cstms.persistence.repositories.StudentRepository;
import com.wego.cstms.persistence.repositories.UserRepository;
import com.wego.cstms.rest.models.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(StudentRepository studentRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
//        saving user for Authentication
        User user = new User();
        user.setUserName(studentDto.getFirstname().concat(studentDto.getLastname()));
        user.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        user.setRoles("STUDENT");
        user.setActive(true);

        userRepository.save(user);
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
