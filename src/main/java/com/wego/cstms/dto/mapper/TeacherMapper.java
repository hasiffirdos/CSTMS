package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.dto.models.TeacherDto;
import com.wego.cstms.persistence.Entities.Student;
import com.wego.cstms.persistence.Entities.Teacher;

import java.util.Date;

public class TeacherMapper {
    public static TeacherDto toTeacherDto(Teacher teacher){
        return new TeacherDto()
                .setId(teacher.getId())
                .setFirstname(teacher.getFirstname())
                .setLastname(teacher.getLastname())
                .setEmail(teacher.getEmail())
                .setPhone(teacher.getPhone())
                .setDob(teacher.getDob())
                .setAge(teacher.getAge());
    }


    public static Teacher toTeacher(TeacherDto teacherDto) {
        return new Teacher()
                .setFirstname(teacherDto.getFirstname())
                .setLastname(teacherDto.getLastname())
                .setEmail(teacherDto.getEmail())
//                .setPassword(teacherDto.getPassword())
                .setPhone(teacherDto.getPhone())
                .setDob(teacherDto.getDob())
                .setSignupDate(new Date())
                .setAge(teacherDto.getAge());

    }
}


