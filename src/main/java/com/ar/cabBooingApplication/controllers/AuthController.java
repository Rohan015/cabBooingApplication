package com.ar.cabBooingApplication.controllers;

import com.ar.cabBooingApplication.dto.SignupDto;
import com.ar.cabBooingApplication.dto.UserDto;
import com.ar.cabBooingApplication.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    UserDto signUp(@RequestBody SignupDto signupDto){
        return authService.signup(signupDto);
    }
}
