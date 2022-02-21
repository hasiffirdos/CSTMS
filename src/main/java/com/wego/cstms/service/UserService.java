package com.wego.cstms.service;


import com.wego.cstms.persistence.Entities.UserEntity;
import com.wego.cstms.persistence.repositories.AdminRepository;
import com.wego.cstms.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    public int getUserId(String username) {
        UserEntity userEntity = userRepository.findByUserName(username).get();
        return userEntity.getId();
    }
    public List<UserEntity> getAllUsers() {
        List<UserEntity> userEntities = new ArrayList<>();
        userRepository.findAll().forEach(userEntities::add);
        return userEntities;
    }
}