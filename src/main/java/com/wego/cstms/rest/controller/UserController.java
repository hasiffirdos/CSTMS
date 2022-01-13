package com.wego.cstms.rest.controller;

import com.wego.cstms.persistence.Entities.Admin;
import com.wego.cstms.persistence.Entities.User;
import com.wego.cstms.rest.models.AdminDto;
import com.wego.cstms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    private final AdminService adminService;


    @Autowired
    public UserController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsers() {
        return adminService.getAllUsers();
    }

    @RequestMapping("/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Admin> getAllAdmins(Principal principal) {
        System.out.println(principal);
        return adminService.getAllAdmins();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public void addAdmin(@RequestBody AdminDto adminDto){
        adminService.addAdmin(adminDto);
    }


//    @RequestMapping(method = RequestMethod.POST, value = "/admins/add-admin")
//    @RequestMapping(method = RequestMethod.POST, value = "/")
//    @RequestMapping(method = RequestMethod.POST, value = "/")

}
