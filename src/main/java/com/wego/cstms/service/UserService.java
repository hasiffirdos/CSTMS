package com.wego.cstms.service;


import com.wego.cstms.dto.mapper.AdminMapper;
import com.wego.cstms.dto.models.AdminDto;
import com.wego.cstms.persistence.Entities.Admin;
import com.wego.cstms.persistence.Entities.User;
import com.wego.cstms.persistence.repositories.AdminRepository;
import com.wego.cstms.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    public int getUserId(String username) {
        User user = userRepository.findByUserName(username).get();
        return user.getId();
    }
}