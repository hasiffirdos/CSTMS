package com.wego.cstms.rest.controller;

import com.wego.cstms.dto.response.CustomResponse;
import com.wego.cstms.dto.models.AdminDto;
import com.wego.cstms.service.AdminService;
import com.wego.cstms.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }


    @RequestMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomResponse> getUsers() {
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .payLoad(userService.getAllUsers())
                        .build()
        );
    }

    @RequestMapping("/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomResponse> getAllAdmins() {
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .payLoad(adminService.getAllAdmins())
                        .build()
        );
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomResponse> addAdmin(@RequestBody AdminDto adminDto) {
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .payLoad(adminService.addAdmin(adminDto))
                        .build()
        );
    }
}
