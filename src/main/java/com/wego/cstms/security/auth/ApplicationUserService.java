package com.wego.cstms.security.auth;

import com.wego.cstms.persistence.Entities.User;
import com.wego.cstms.persistence.repositories.UserRepository;
import com.wego.cstms.security.base.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


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
        Optional<User> user = userRepository.findByUserName(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Not Found: "+ username));
        return user.map(ApplicationUser::new).get();

//        TODO: user = teacher or student
//        TODO: return UserDetails(user)
//        return applicationUserDao
//                .selectApplicationUserByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found",username)));
    }
}
