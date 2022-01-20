package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.CourseMapper;
import com.wego.cstms.dto.mapper.TeacherMapper;
import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.Teacher;
import com.wego.cstms.persistence.Entities.User;
import com.wego.cstms.persistence.repositories.TeacherRepository;
import com.wego.cstms.persistence.repositories.UserRepository;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<TeacherDto> getAllTeachers(){
        List<TeacherDto> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach((teacher)->{
            teachers.add(TeacherMapper.toTeacherDto(teacher));
        });
        return teachers;
    }

    public TeacherDto getTeacher(Integer id) {
        return TeacherMapper.toTeacherDto(teacherRepository.findById(id).get());
    }

    public void addTeacher(TeacherDto teacherDto) {
        Teacher teacher = TeacherMapper.toTeacher(teacherDto);
        User user = new User();
        user.setUserName(teacher.getFirstname().concat(teacher.getLastname()));
        user.setPassword(passwordEncoder.encode(teacher.getPassword()));
        user.setRole("TEACHER");
        user.setActive(true);

        userRepository.save(user);
        teacher.setId(user.getId());
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }

    public List<CourseDto> getTeacherCourses(int teacherId) {
        List<CourseDto> courseDtos = new ArrayList<>();
        teacherRepository.findById(teacherId).get().getTaughtCourses().forEach((course)->{
            courseDtos.add(CourseMapper.toCourseDto(course));
        });
        return courseDtos;
    }

    public void addTeachersCourse(CourseDto courseDto, int teacherId){
        Teacher teacher = teacherRepository.findById(teacherId).get();
        teacher.getTaughtCourses().add(CourseMapper.toCourse(courseDto));
        teacherRepository.save(teacher);
    }
}
