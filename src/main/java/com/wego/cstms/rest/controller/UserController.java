package com.wego.cstms.rest.controller;

import com.wego.cstms.dto.response.CustomResponse;
import com.wego.cstms.dto.models.AdminDto;
import com.wego.cstms.exceptions.MSException;
import com.wego.cstms.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final AdminService adminService;


    public UserController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomResponse> getUsers() {
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .payLoad(adminService.getAllUsers())
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
