package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.persistence.Entities.Student;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

public class StudentMapper {
    public static StudentDto toStudentDto(Student student) {
        return new StudentDto()
                .setId(student.getId())
                .setFirstname(student.getFirstname())
                .setLastname(student.getLastname())
                .setEmail(student.getEmail())
                .setPhone(student.getPhone())
                .setDob(student.getDob())
                .setAge(student.getAge());
    }

    public static Student toStudent(StudentDto studentDto, PasswordEncoder passwordEncoder) {
        Student student = new Student();
        student.setFirstname(studentDto.getFirstname());
        student.setLastname(studentDto.getLastname());
        student.setEmail(studentDto.getEmail());
        student.setPhone(studentDto.getPhone());
        student.setDob(studentDto.getDob());
        student.setSignupDate(new Date());
        student.setAge(studentDto.getAge());
        student.setUserName(studentDto.getUsername());
        student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        student.setRole("STUDENT");
        student.setActive(true);
        return student;

    }
}



