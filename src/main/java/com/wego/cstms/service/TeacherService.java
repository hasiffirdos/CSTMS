package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.CourseMapper;
import com.wego.cstms.dto.mapper.TeacherMapper;
import com.wego.cstms.exceptions.EntityType;
import com.wego.cstms.exceptions.MSException;
import com.wego.cstms.persistence.Entities.Teacher;
import com.wego.cstms.persistence.repositories.TeacherRepository;
import com.wego.cstms.dto.models.CourseDto;
import com.wego.cstms.dto.models.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final MSException msException;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, MSException msException, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.msException = msException;
        this.passwordEncoder = passwordEncoder;
    }

    public List<TeacherDto> getAllTeachers() {
        List<TeacherDto> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach((teacher) -> {
            teachers.add(TeacherMapper.toTeacherDto(teacher));
        });
        return teachers;
    }

    public TeacherDto getTeacher(Integer id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher != null) {
            return TeacherMapper.toTeacherDto(teacher);
        }
        return null;
    }

    public TeacherDto addTeacher(TeacherDto teacherDto) {
        if(!teacherRepository.existsByUserName(teacherDto.getUsername())){
            Teacher teacher = TeacherMapper.toTeacher(teacherDto,passwordEncoder);
            teacherRepository.save(teacher);
            return teacherDto;
        }
        throw msException.EntityAlreadyExistsException(EntityType.TEACHER,teacherDto.getUsername());
    }

    public void updateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public Boolean deleteTeacher(Integer id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CourseDto> getTeacherCourses(int teacherId) {
        List<CourseDto> courseDtos = new ArrayList<>();
        teacherRepository.findById(teacherId).ifPresent(teacher -> teacher.getTaughtCourses().forEach((course) ->
                courseDtos.add(CourseMapper.toCourseDto(course))));
        return courseDtos;
    }

    public String addTeachersCourse(CourseDto courseDto, int teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        if (teacher != null) {
            teacher.getTaughtCourses().add(CourseMapper.toCourse(courseDto));
            teacherRepository.save(teacher);
            return String.format("%s has been Added of Teacher: %s", courseDto.getName(), teacher.getUserName());
        }
        throw msException.EntityNotFoundException(EntityType.TEACHER,teacherId);
    }
}
