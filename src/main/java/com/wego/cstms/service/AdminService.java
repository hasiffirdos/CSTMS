package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.AdminMapper;
import com.wego.cstms.exceptions.EntityType;
import com.wego.cstms.exceptions.MSException;
import com.wego.cstms.persistence.Entities.Admin;
import com.wego.cstms.persistence.Entities.User;
import com.wego.cstms.persistence.repositories.AdminRepository;
import com.wego.cstms.persistence.repositories.UserRepository;
import com.wego.cstms.dto.models.AdminDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {


    private final UserRepository userRepository;
    private final MSException msException;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(UserRepository userRepository, MSException msException, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.msException = msException;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public List<Admin> getAllAdmins() {
        List<Admin> adminEntities = new ArrayList<>();
        adminRepository.findAll().forEach(adminEntities::add);
        return adminEntities;
    }

    public AdminDto addAdmin(AdminDto adminDto) {
        if (adminRepository.existsByUserName(adminDto.getUsername())) {
            Admin admin = AdminMapper.toAdmin(adminDto,passwordEncoder);
            adminRepository.save(admin);
            return adminDto;
        }
        throw msException.EntityAlreadyExistsException(EntityType.ADMIN,adminDto.getUsername());
    }
}
