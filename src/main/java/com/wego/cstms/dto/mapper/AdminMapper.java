package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.AdminDto;
import com.wego.cstms.persistence.Entities.AdminEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

public class AdminMapper {
    public static AdminDto toAdminDto(AdminEntity adminEntity) {
        return new AdminDto()
                .setFirstname(adminEntity.getFirstname())
                .setLastname(adminEntity.getLastname())
                .setEmail(adminEntity.getEmail())
                .setPhone(adminEntity.getPhone())
                .setDob(adminEntity.getDob())
                .setAge(adminEntity.getAge());
    }

    public static AdminEntity toAdmin(AdminDto adminDto, PasswordEncoder passwordEncoder) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setFirstname(adminDto.getFirstname());
        adminEntity.setLastname(adminDto.getLastname());
        adminEntity.setEmail(adminDto.getEmail());
        adminEntity.setPhone(adminDto.getPhone());
        adminEntity.setDob(adminDto.getDob());
        adminEntity.setSignupDate(new Date());
        adminEntity.setAge(adminDto.getAge());
        adminEntity.setUserName(adminEntity.getUserName());
        adminEntity.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        adminEntity.setRole("ADMIN");
        adminEntity.setActive(true);
        return adminEntity;
    }
}


