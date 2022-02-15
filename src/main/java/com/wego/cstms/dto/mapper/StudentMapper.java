package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.persistence.Entities.Student;

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

    public static Student toStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setFirstname(studentDto.getFirstname());
        student.setLastname(studentDto.getLastname());
        student.setEmail(studentDto.getEmail());
//                student.setPassword(studentDto.getPassword());
        student.setPhone(studentDto.getPhone());
        student.setDob(studentDto.getDob());
        student.setSignupDate(new Date());
        student.setAge(studentDto.getAge());
        return student;

    }
}



