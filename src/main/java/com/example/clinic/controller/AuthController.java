package com.example.clinic.controller;

import com.example.clinic.dto.loginDto.LoginDto;
import com.example.clinic.dto.loginDto.RegisterDto;
import com.example.clinic.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/api/v1/auth/register")
    public String registerUser(@Valid @RequestBody RegisterDto dto){
        return authService.register(dto);
    }

    @PostMapping("/api/v1/auth/login")
    public String login(@RequestBody LoginDto dto){
        return authService.login(dto);
    }
}
