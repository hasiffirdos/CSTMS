package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.TeacherDto;
import com.wego.cstms.persistence.Entities.TeacherEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

public class TeacherMapper {
    public static TeacherDto toTeacherDto(TeacherEntity teacherEntity) {
        return new TeacherDto()
                .setId(teacherEntity.getId())
                .setFirstname(teacherEntity.getFirstname())
                .setLastname(teacherEntity.getLastname())
                .setEmail(teacherEntity.getEmail())
                .setPhone(teacherEntity.getPhone())
                .setDob(teacherEntity.getDob())
                .setAge(teacherEntity.getAge());
    }


    public static TeacherEntity toTeacher(TeacherDto teacherDto, PasswordEncoder passwordEncoder) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setFirstname(teacherDto.getFirstname());
        teacherEntity.setLastname(teacherDto.getLastname());
        teacherEntity.setEmail(teacherDto.getEmail());
        teacherEntity.setPhone(teacherDto.getPhone());
        teacherEntity.setDob(teacherDto.getDob());
        teacherEntity.setSignupDate(new Date());
        teacherEntity.setAge(teacherDto.getAge());
        teacherEntity.setUserName(teacherDto.getUsername());
        teacherEntity.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
        teacherEntity.setRole("TEACHER");
        teacherEntity.setActive(true);
        return teacherEntity;
    }
}


