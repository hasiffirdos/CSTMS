package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.CourseMapper;
import com.wego.cstms.dto.mapper.StudentMapper;
import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.exceptions.EntityType;
import com.wego.cstms.exceptions.MSException;
import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.StudentEntity;
import com.wego.cstms.persistence.repositories.CourseRepository;
import com.wego.cstms.dto.models.CourseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final MSException msException;

    public CourseService(CourseRepository courseRepository, MSException msException) {
        this.courseRepository = courseRepository;
        this.msException = msException;
    }

    public List<CourseDto> getAllCourses() {
        List<CourseDto> courseDtos = new ArrayList<>();
        courseRepository.findAll().forEach(c -> {
            courseDtos.add(CourseMapper.toCourseDto(c));
        });
        return courseDtos;
    }

    public List<CourseDto> getTopAllTimes(int numberOfCourses) {
        List<Course> cours = new ArrayList<>(courseRepository.getTopAllTimes());
        List<CourseDto> selectedCourses = new ArrayList<>();
        cours.stream().limit(numberOfCourses).collect(Collectors.toList()).forEach(c -> {
            selectedCourses.add(CourseMapper.toCourseDto(c));
        });
        return selectedCourses;
    }

    public List<CourseDto> getTopTrending(int numberOfCourses) {
        List<Course> cours = new ArrayList<>(courseRepository.getTopTrending());
        List<CourseDto> selectedCourses = new ArrayList<>();
        cours.stream().limit(numberOfCourses).collect(Collectors.toList()).forEach(c -> {
            selectedCourses.add(CourseMapper.toCourseDto(c));
        });
        return selectedCourses;
    }

    public CourseDto getCourse(Integer id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return CourseMapper.toCourseDto(course.get());
        }
        throw msException.EntityNotFoundException(EntityType.COURSE, id);
    }

//    public void addCourse(CourseDto courseDto) {
//
//        Course course = CourseMapper.toCourse(courseDto);
//        courseRepository.save(course);
//    }
//
//    public void updateCourse(CourseDto courseDto) {
//
//        Course course = CourseMapper.toCourse(courseDto);
//        courseRepository.save(course);
//    }

    public String deleteCourse(Integer id) {
//        Optional<Course> course = courseRepository.existsById(id);
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return String.format("Course with Id:%d deleted successfully.", id);
        }
        throw msException.EntityNotFoundException(EntityType.COURSE,id);
    }

    public List<StudentDto> getRegisteredStudents(int courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            List<StudentDto> studentDtos = new ArrayList<>();
            List<StudentEntity> studentEntities = course.get().getRegisteredStudents();
            if (studentEntities.size() > 0) {
                studentEntities.forEach(s -> {
                    studentDtos.add(StudentMapper.toStudentDto(s));
                });
                return studentDtos;
            }
        }
        throw msException.EntityNotFoundException(EntityType.COURSE, courseId);
    }
}
