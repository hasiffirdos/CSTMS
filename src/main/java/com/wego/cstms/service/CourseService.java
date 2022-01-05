package com.wego.cstms.service;


import com.wego.cstms.persistence.models.Course;
import com.wego.cstms.persistence.models.Teacher;
import com.wego.cstms.persistence.repositories.CourseRepository;
import com.wego.cstms.persistence.repositories.TeacherRepository;
import com.wego.cstms.rest.models.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    public Course getUser(Integer id) {
        return courseRepository.findById(id).get();
    }

    public void addCourse(CourseDto courseDto) {
        List<Integer> teachersIds = courseDto.getTeacher();
        List<Teacher> teachers = new ArrayList<>();

        teacherRepository.findAllById(teachersIds).forEach((teacher) -> {
            teachers.add(teacher);
        });

        Course course = new Course(courseDto, teachers);
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }
}
