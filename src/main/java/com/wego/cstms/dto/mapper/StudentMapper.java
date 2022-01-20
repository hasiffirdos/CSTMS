package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.persistence.Entities.Student;

import java.util.Date;

public class StudentMapper {
    public static StudentDto toStudentDto(Student student) {
        return new StudentDto()
                .setFirstname(student.getFirstname())
                .setLastname(student.getLastname())
                .setEmail(student.getEmail())
                .setPhone(student.getPhone())
                .setDob(student.getDob())
                .setAge(student.getAge());
    }

    public static Student toStudent(StudentDto studentDto) {
        return new Student()
                .setFirstname(studentDto.getFirstname())
                .setLastname(studentDto.getLastname())
                .setEmail(studentDto.getEmail())
                .setPassword(studentDto.getPassword())
                .setPhone(studentDto.getPhone())
                .setDob(studentDto.getDob())
                .setSignupDate(new Date())
                .setAge(studentDto.getAge());

    }
}



