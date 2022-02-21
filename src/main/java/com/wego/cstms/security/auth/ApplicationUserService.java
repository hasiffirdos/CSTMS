package com.wego.cstms.security.auth;

import com.wego.cstms.persistence.Entities.UserEntity;
import com.wego.cstms.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ApplicationUserService implements UserDetailsService {


    private final ApplicationUserDao applicationUserDao;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public ApplicationUserService(@Qualifier("fake") ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUserName(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Not Found: "+ username));
        return user.map(ApplicationUser::new).get();

//        TODO: user = teacher or student
//        TODO: return UserDetails(user)
//        return applicationUserDao
//                .selectApplicationUserByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found",username)));
    }
}
