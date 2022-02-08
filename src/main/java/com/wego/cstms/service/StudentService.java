package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.StudentMapper;
import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.Student;
import com.wego.cstms.persistence.repositories.CourseRepository;
import com.wego.cstms.persistence.repositories.StudentRepository;
import com.wego.cstms.persistence.repositories.UserRepository;
import com.wego.cstms.dto.models.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(StudentRepository studentRepository, UserRepository userRepository, CourseRepository courseRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<StudentDto> getAllStudents() {
        List<StudentDto> students = new ArrayList<>();
        studentRepository.findAll().forEach((student)->{
            students.add(StudentMapper.toStudentDto(student));
        });
        return students;
    }

    public StudentDto getStudent(Integer id) {
        return StudentMapper.toStudentDto(studentRepository.findById(id).get());
    }

    public void addStudent(StudentDto studentDto) {

        Student student = StudentMapper.toStudent(studentDto);
//        saving user for Authentication
//        User user = new User();
//        user.setUserName(studentDto.getFirstname().concat(studentDto.getLastname()));
//        user.setPassword(passwordEncoder.encode(studentDto.getPassword()));
//        user.setRole("STUDENT");
//        user.setActive(true);
        student.setUserName(studentDto.getFirstname().concat(studentDto.getLastname()));
        student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        student.setRole("STUDENT");
        student.setActive(true);

//        userRepository.save(user);
//        student.setId(user.getId());
        studentRepository.save(student);
    }

    public void addStudentCourse(int studentId, int courseId){
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();
        student.getEnrolledCourses().add(course);
        studentRepository.save(student);
    }

    public void removeStudentCourse(int studentId, int courseId){
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();
        student.getEnrolledCourses().remove(course);
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
