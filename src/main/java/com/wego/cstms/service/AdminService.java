package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.AdminMapper;
import com.wego.cstms.exceptions.EntityType;
import com.wego.cstms.exceptions.MSException;
import com.wego.cstms.persistence.Entities.AdminEntity;
import com.wego.cstms.persistence.Entities.UserEntity;
import com.wego.cstms.persistence.repositories.AdminRepository;
import com.wego.cstms.persistence.repositories.UserRepository;
import com.wego.cstms.dto.models.AdminDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {



    private final MSException msException;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(MSException msException, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {

        this.msException = msException;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AdminEntity> getAllAdmins() {
        List<AdminEntity> adminEntities = new ArrayList<>();
        adminRepository.findAll().forEach(adminEntities::add);
        return adminEntities;
    }

    public AdminDto addAdmin(AdminDto adminDto) {
        if (adminRepository.existsByUserName(adminDto.getUsername())) {
            AdminEntity adminEntity = AdminMapper.toAdmin(adminDto,passwordEncoder);
            adminRepository.save(adminEntity);
            return adminDto;
        }
        throw msException.EntityAlreadyExistsException(EntityType.ADMIN,adminDto.getUsername());
    }
}
