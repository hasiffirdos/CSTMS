package com.wego.cstms.service;


import com.wego.cstms.persistence.Entities.Admin;
import com.wego.cstms.persistence.Entities.User;
import com.wego.cstms.persistence.repositories.AdminRepository;
import com.wego.cstms.persistence.repositories.UserRepository;
import com.wego.cstms.rest.models.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {


    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(UserRepository userRepository, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        adminRepository.findAll().forEach(admins::add);
        return admins;
    }

    public void addAdmin(AdminDto adminDto){
        Admin admin=new Admin(adminDto);
        User user = new User();
        user.setUserName(adminDto.getFirstname()+adminDto.getLastname());
        user.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        user.setRoles("ADMIN");
        user.setActive(true);
        userRepository.save(user);
        admin.setId(user.getId());

        adminRepository.save(admin);

    }

//    public Student getStudent(Integer id) {
//        return studentRepository.findById(id).get();
//    }
//
//    public void addStudent(StudentDto studentDto) {
//
//        Student student = new Student(studentDto);
////        saving user for Authentication
//        User user = new User();
//        user.setUserName(studentDto.getFirstname().concat(studentDto.getLastname()));
//        user.setPassword(studentDto.getPassword());
//        user.setRoles("STUDENT");
//        user.setActive(true);
//
//        userRepository.save(user);
//        studentRepository.save(student);
//    }
//
//    public void updateStudent(Student student) {
//        studentRepository.save(student);
//    }
//
//    public void deleteStudent(Integer id) {
//        studentRepository.deleteById(id);
//    }
//
//    public List<Course> getEnrolledCourses(int studentId) {
//        return studentRepository.findById(studentId).get().getEnrolledCourses();
//    }
}
