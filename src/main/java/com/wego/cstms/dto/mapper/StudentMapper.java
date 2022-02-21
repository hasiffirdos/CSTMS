package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.persistence.Entities.StudentEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

public class StudentMapper {
    public static StudentDto toStudentDto(StudentEntity studentEntity) {
        return new StudentDto()
                .setId(studentEntity.getId())
                .setFirstname(studentEntity.getFirstname())
                .setLastname(studentEntity.getLastname())
                .setEmail(studentEntity.getEmail())
                .setPhone(studentEntity.getPhone())
                .setDob(studentEntity.getDob())
                .setAge(studentEntity.getAge());
    }

    public static StudentEntity toStudent(StudentDto studentDto, PasswordEncoder passwordEncoder) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstname(studentDto.getFirstname());
        studentEntity.setLastname(studentDto.getLastname());
        studentEntity.setEmail(studentDto.getEmail());
        studentEntity.setPhone(studentDto.getPhone());
        studentEntity.setDob(studentDto.getDob());
        studentEntity.setSignupDate(new Date());
        studentEntity.setAge(studentDto.getAge());
        studentEntity.setUserName(studentDto.getUsername());
        studentEntity.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        studentEntity.setRole("STUDENT");
        studentEntity.setActive(true);
        return studentEntity;

    }
}



