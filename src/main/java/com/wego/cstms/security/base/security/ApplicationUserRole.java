package com.wego.cstms.security.base.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.wego.cstms.security.base.security.ApplicationUserPermission.*;


public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet(COURSE_READ, STUDENT_READ, STUDENT_WRITE)),
    ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE, STUDENT_WRITE, STUDENT_READ,COURSE_READ,COURSE_WRITE)),
    TEACHER(Sets.newHashSet(COURSE_READ,COURSE_WRITE, TEACHER_WRITE, TEACHER_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority((permission.getPermission())))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
