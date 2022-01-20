package com.wego.cstms.security.principal;

import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.Student;
import com.wego.cstms.persistence.Entities.User;
import com.wego.cstms.persistence.repositories.CourseRepository;
import com.wego.cstms.persistence.repositories.StudentRepository;
import com.wego.cstms.persistence.repositories.UserRepository;
import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("principalSecurity")
public class PrincipalCheckLayer {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    @Autowired
    public PrincipalCheckLayer(UserRepository userRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public boolean hasUserId(Authentication authentication, int userId) {
        User user = userRepository.findById(userId).get();
        if (authentication.getName().equals(user.getUserName())) {
            return true;
        }
        return false;
    }

    public boolean hasEnrolledCourse(Authentication authentication, int studentId, int courseId) {
        if (hasUserId(authentication,studentId)){
            Student student = studentRepository.findById(studentId).get();
            for (Course course : student.getEnrolledCourses()) {
                if (course.getId() == courseId) {
                    return true;
                }
            }
        }
        return false;
    }

}
