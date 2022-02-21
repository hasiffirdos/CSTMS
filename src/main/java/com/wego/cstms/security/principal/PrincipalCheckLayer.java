package com.wego.cstms.security.principal;

import com.wego.cstms.persistence.Entities.CourseEntity;
import com.wego.cstms.persistence.Entities.StudentEntity;
import com.wego.cstms.persistence.Entities.TeacherEntity;
import com.wego.cstms.persistence.Entities.UserEntity;
import com.wego.cstms.persistence.repositories.CourseRepository;
import com.wego.cstms.persistence.repositories.StudentRepository;
import com.wego.cstms.persistence.repositories.TeacherRepository;
import com.wego.cstms.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("principalSecurity")
public class PrincipalCheckLayer {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;


    @Autowired
    public PrincipalCheckLayer(UserRepository userRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public boolean hasUserId(Authentication authentication, int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        return user.filter(value -> authentication.getName().equals(value.getUserName())).isPresent();
    }

    public boolean hasEnrolledCourse(Authentication authentication, int studentId, int courseId) {
        if (hasUserId(authentication, studentId)) {
            StudentEntity studentEntity = studentRepository.findById(studentId).get();
            for (CourseEntity CourseEntity : studentEntity.getEnrolledCourses()) {
                if (CourseEntity.getId() == courseId) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean hasCourseOwnership(Authentication authentication, int teacherId, int courseId) {
        if (hasUserId(authentication, teacherId)) {
            Optional<TeacherEntity> teacher = teacherRepository.findById(teacherId);
            if (teacher.isPresent()) {
                for (CourseEntity courseEntity : teacher.get().getTaughtCourses()) {
                    if (courseEntity.getId() == courseId) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public boolean isCourseOwner(Authentication authentication, int courseId) {
        Optional<CourseEntity> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            Optional<TeacherEntity> optionalTeacher = teacherRepository.findByUserName(authentication.getName());
            if (optionalTeacher.isPresent()) {
                return course.get().getTeachers().contains(optionalTeacher.get());
            }
        }
        return false;
    }

}
