package com.wego.cstms.security.auth;

//import com.google.common.collect.Lists;
import com.google.common.collect.Lists;
import com.wego.cstms.security.base.security.ApplicationUserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("fake")     // fake is just a name
public class FakeApplicationUserDaoService implements ApplicationUserDao{

    final private PasswordEncoder passwordEncoder;

    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUser()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUser(){
        List<ApplicationUser> applicationUsers =
                Lists.newArrayList(
                new ApplicationUser(
                        passwordEncoder.encode( "somepass"),
                        "Asiffirdos@email.com",
                        ApplicationUserRole.ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                        ),
                new ApplicationUser(
                        passwordEncoder.encode( "somepass"),
                        "farooq",
                        ApplicationUserRole.STUDENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        passwordEncoder.encode( "somepass"),
                        "hassan",
                        ApplicationUserRole.TEACHER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        passwordEncoder.encode( "somepass"),
                        "abubakar",
                        ApplicationUserRole.STUDENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        passwordEncoder.encode( "somepass"),
                        "abdullah",
                        ApplicationUserRole.STUDENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        passwordEncoder.encode( "somepass"),
                        "hamza",
                        ApplicationUserRole.STUDENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        passwordEncoder.encode( "somepass"),
                        "waleed",
                        ApplicationUserRole.STUDENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }
}
