package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.AdminDto;
import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.persistence.Entities.Admin;
import com.wego.cstms.persistence.Entities.Student;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

public class AdminMapper {
    public static AdminDto toAdminDto(Admin admin) {
        return new AdminDto()
                .setFirstname(admin.getFirstname())
                .setLastname(admin.getLastname())
                .setEmail(admin.getEmail())
                .setPhone(admin.getPhone())
                .setDob(admin.getDob())
                .setAge(admin.getAge());
    }

    public static Admin toAdmin(AdminDto adminDto, PasswordEncoder passwordEncoder) {
        Admin admin = new Admin();
        admin.setFirstname(adminDto.getFirstname());
        admin.setLastname(adminDto.getLastname());
        admin.setEmail(adminDto.getEmail());
        admin.setPhone(adminDto.getPhone());
        admin.setDob(adminDto.getDob());
        admin.setSignupDate(new Date());
        admin.setAge(adminDto.getAge());
        admin.setUserName(admin.getUserName());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setRole("ADMIN");
        admin.setActive(true);
        return admin;
    }
}


