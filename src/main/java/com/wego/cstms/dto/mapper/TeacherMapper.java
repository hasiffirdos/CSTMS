package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.dto.models.TeacherDto;
import com.wego.cstms.persistence.Entities.Student;
import com.wego.cstms.persistence.Entities.Teacher;

import java.util.Date;

public class TeacherMapper {
    public static TeacherDto toTeacherDto(Teacher teacher) {
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
        Teacher teacher = new Teacher();
        teacher.setFirstname(teacherDto.getFirstname());
        teacher.setLastname(teacherDto.getLastname());
        teacher.setEmail(teacherDto.getEmail());
//                teacher.setPassword(teacherDto.getPassword())
        teacher.setPhone(teacherDto.getPhone());
        teacher.setDob(teacherDto.getDob());
        teacher.setSignupDate(new Date());
        teacher.setAge(teacherDto.getAge());
        return teacher;
    }
}


