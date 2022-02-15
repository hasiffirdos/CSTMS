package com.wego.cstms.security.controller;


import com.wego.cstms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SecurityController {
    private final UserService userService;

    @CrossOrigin
    @RequestMapping("/under-the-hood/silent/request/userid/with/{username}")
    public int getUserId(@PathVariable String username){
        return userService.getUserId(username);
    }
}
