package com.wego.cstms.service;


import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.Teacher;
import com.wego.cstms.persistence.Entities.User;
import com.wego.cstms.persistence.repositories.TeacherRepository;
import com.wego.cstms.persistence.repositories.UserRepository;
import com.wego.cstms.rest.models.CourseDto;
import com.wego.cstms.rest.models.TeacherDto;
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

    public List<Teacher> getAllTeachers(){
        List<Teacher> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teachers::add);
        return teachers;
    }

    public Teacher getTeacher(Integer id) {
        return teacherRepository.findById(id).get();
    }

    public void addTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher(teacherDto);
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

    public List<Course> getTeacherCourses(int teacherId) {
        return teacherRepository.findById(teacherId).get().getTaughtCourses();
    }

    public void addTeachersCourse(CourseDto courseDto, int teacherId){
        Teacher teacher = teacherRepository.findById(teacherId).get();
        teacher.getTaughtCourses().add(new Course(courseDto));
        teacherRepository.save(teacher);
    }
}
