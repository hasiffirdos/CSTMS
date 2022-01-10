package com.wego.cstms.service;


import com.wego.cstms.persistence.models.Course;
import com.wego.cstms.persistence.models.Teacher;
import com.wego.cstms.persistence.repositories.TeacherRepository;
import com.wego.cstms.rest.models.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
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
}
