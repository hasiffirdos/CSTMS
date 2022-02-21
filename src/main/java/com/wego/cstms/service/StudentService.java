package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.StudentMapper;
import com.wego.cstms.exceptions.EntityType;
import com.wego.cstms.exceptions.MSException;
import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.Student;
import com.wego.cstms.persistence.repositories.CourseRepository;
import com.wego.cstms.persistence.repositories.StudentRepository;
import com.wego.cstms.dto.models.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            return StudentMapper.toStudentDto(student);
        }
        throw msException.EntityNotFoundException(EntityType.STUDENT, id);

    }

    public StudentDto addStudent(StudentDto studentDto) {
        if (!studentRepository.existsByUserName(studentDto.getUsername())) {
            Student student = StudentMapper.toStudent(studentDto, passwordEncoder);
            studentRepository.save(student);
            return studentDto;
        }
        throw msException.EntityAlreadyExistsException(EntityType.STUDENT, studentDto.getUsername());
    }

    public String addStudentCourse(int studentId, int courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        if (student != null) {
            if (course != null) {
                student.getEnrolledCourses().add(course);
                studentRepository.save(student);
                return String.format("%s has enrolled for Course:%s", student.getUserName(), course.getName());
            }
            throw msException.EntityNotFoundException(EntityType.COURSE, courseId);
        }
        throw msException.EntityNotFoundException(EntityType.STUDENT, studentId);
    }

    public String removeStudentCourse(int studentId, int courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        if (student != null) {
            if (course != null) {
                student.getEnrolledCourses().remove(course);
                studentRepository.save(student);
                return String.format("%s has optOut for Course:%s", student.getUserName(), course.getName());
            }
            throw msException.EntityNotFoundException(EntityType.COURSE, courseId);
        }
        throw msException.EntityNotFoundException(EntityType.STUDENT, studentId);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    public Boolean deleteStudent(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Course> getEnrolledCourses(int studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            return student.getEnrolledCourses();
        }
        return null;
    }
}
