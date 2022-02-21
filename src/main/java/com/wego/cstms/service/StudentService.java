package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.StudentMapper;
import com.wego.cstms.exceptions.EntityType;
import com.wego.cstms.exceptions.MSException;
import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.StudentEntity;
import com.wego.cstms.persistence.repositories.CourseRepository;
import com.wego.cstms.persistence.repositories.StudentRepository;
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
    private final MSException msException;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, PasswordEncoder passwordEncoder, MSException msException) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
        this.msException = msException;
    }

    public List<StudentDto> getAllStudents() {
        List<StudentDto> students = new ArrayList<>();
        studentRepository.findAll().forEach((student) ->
                students.add(StudentMapper.toStudentDto(student)));
        return students;
    }

    public StudentDto getStudent(Integer id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);
        if (studentEntity != null) {
            return StudentMapper.toStudentDto(studentEntity);
        }
        throw msException.EntityNotFoundException(EntityType.STUDENT, id);

    }

    public StudentDto addStudent(StudentDto studentDto) {
        if (!studentRepository.existsByUserName(studentDto.getUsername())) {
            StudentEntity studentEntity = StudentMapper.toStudent(studentDto, passwordEncoder);
            studentRepository.save(studentEntity);
            return studentDto;
        }
        throw msException.EntityAlreadyExistsException(EntityType.STUDENT, studentDto.getUsername());
    }

    public String addStudentCourse(int studentId, int courseId) {
        StudentEntity studentEntity = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        if (studentEntity != null) {
            if (course != null) {
                studentEntity.getEnrolledCourses().add(course);
                studentRepository.save(studentEntity);
                return String.format("%s has enrolled for Course:%s", studentEntity.getUserName(), course.getName());
            }
            throw msException.EntityNotFoundException(EntityType.COURSE, courseId);
        }
        throw msException.EntityNotFoundException(EntityType.STUDENT, studentId);
    }

    public String removeStudentCourse(int studentId, int courseId) {
        StudentEntity studentEntity = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        if (studentEntity != null) {
            if (course != null) {
                studentEntity.getEnrolledCourses().remove(course);
                studentRepository.save(studentEntity);
                return String.format("%s has optOut for Course:%s", studentEntity.getUserName(), course.getName());
            }
            throw msException.EntityNotFoundException(EntityType.COURSE, courseId);
        }
        throw msException.EntityNotFoundException(EntityType.STUDENT, studentId);
    }

    public void updateStudent(StudentEntity studentEntity) {
        studentRepository.save(studentEntity);
    }

    public Boolean deleteStudent(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Course> getEnrolledCourses(int studentId) {
        StudentEntity studentEntity = studentRepository.findById(studentId).orElse(null);
        if (studentEntity != null) {
            return studentEntity.getEnrolledCourses();
        }
        return null;
    }
}
