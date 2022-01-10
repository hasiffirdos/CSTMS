package com.wego.cstms.service;


import com.wego.cstms.persistence.models.Course;
import com.wego.cstms.persistence.models.Student;
import com.wego.cstms.persistence.repositories.CourseRepository;
import com.wego.cstms.persistence.repositories.TeacherRepository;
import com.wego.cstms.rest.models.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Course> getTopAllTimes(int numberOfCourses) {
        List<Course> courses = new ArrayList<>();
        courseRepository.getTopAllTimes().forEach(courses::add);
        return courses.stream().limit(numberOfCourses).collect(Collectors.toList());
    }

    public List<Course> getTopTrending(int numberOfCourses) {
        List<Course> courses = new ArrayList<>();
        courseRepository.getTopTrending().forEach(courses::add);
        return courses.stream().limit(numberOfCourses).collect(Collectors.toList());
    }

    public Course getCourse(Integer id) {
        return courseRepository.findById(id).get();
    }

    public void addCourse(CourseDto courseDto) {
//        List<Integer> teachersIds = courseDto.getTeacher();
//        List<Teacher> teachers = new ArrayList<>();
//
//        teacherRepository.findAllById(teachersIds).forEach((teacher) -> {
//            teachers.add(teacher);
//        });
//
//        Course course = new Course(courseDto, teachers);
        Course course = new Course(courseDto);
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    public List<Student> getRegisteredStudents(int courseId) {
        return courseRepository.findById(courseId).get().getRegisteredStudents();
    }
}
