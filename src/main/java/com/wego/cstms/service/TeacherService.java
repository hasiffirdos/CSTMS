package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.CourseMapper;
import com.wego.cstms.dto.mapper.TeacherMapper;
import com.wego.cstms.exceptions.EntityType;
import com.wego.cstms.exceptions.MSException;
import com.wego.cstms.persistence.Entities.TeacherEntity;
import com.wego.cstms.persistence.repositories.TeacherRepository;
import com.wego.cstms.dto.models.CourseDto;
import com.wego.cstms.dto.models.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        TeacherEntity teacherEntity = teacherRepository.findById(id).orElse(null);
        if (teacherEntity != null) {
            return TeacherMapper.toTeacherDto(teacherEntity);
        }
        return null;
    }

    public TeacherDto addTeacher(TeacherDto teacherDto) {
        if(!teacherRepository.existsByUserName(teacherDto.getUsername())){
            TeacherEntity teacherEntity = TeacherMapper.toTeacher(teacherDto,passwordEncoder);
            teacherRepository.save(teacherEntity);
            return teacherDto;
        }
        throw msException.EntityAlreadyExistsException(EntityType.TEACHER,teacherDto.getUsername());
    }

    public void updateTeacher(TeacherEntity teacherEntity) {
        teacherRepository.save(teacherEntity);
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
        TeacherEntity teacherEntity = teacherRepository.findById(teacherId).orElse(null);
        if (teacherEntity != null) {
            teacherEntity.getTaughtCourses().add(CourseMapper.toCourse(courseDto));
            teacherRepository.save(teacherEntity);
            return String.format("%s has been Added of Teacher: %s", courseDto.getName(), teacherEntity.getUserName());
        }
        throw msException.EntityNotFoundException(EntityType.TEACHER,teacherId);
    }
}
