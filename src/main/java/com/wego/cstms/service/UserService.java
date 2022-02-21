package com.wego.cstms.service;


import com.wego.cstms.persistence.Entities.User;
import com.wego.cstms.persistence.repositories.AdminRepository;
import com.wego.cstms.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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