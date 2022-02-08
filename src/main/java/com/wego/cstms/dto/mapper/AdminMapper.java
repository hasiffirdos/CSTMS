package com.wego.cstms.dto.mapper;

import com.wego.cstms.dto.models.AdminDto;
import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.persistence.Entities.Admin;
import com.wego.cstms.persistence.Entities.Student;

import java.util.Date;

public class AdminMapper {
    public static AdminDto toAdminDto(Admin admin){
        return new AdminDto()
                .setFirstname(admin.getFirstname())
                .setLastname(admin.getLastname())
                .setEmail(admin.getEmail())
                .setPhone(admin.getPhone())
                .setDob(admin.getDob())
                .setAge(admin.getAge());
    }

    public static Admin toAdmin(AdminDto adminDto) {
        return new Admin()
                .setFirstname(adminDto.getFirstname())
                .setLastname(adminDto.getLastname())
                .setEmail(adminDto.getEmail())
//                .setPassword(adminDto.getPassword())
                .setPhone(adminDto.getPhone())
                .setDob(adminDto.getDob())
                .setSignupDate(new Date())
                .setAge(adminDto.getAge());

    }
}


