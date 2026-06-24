package com.example.clinic.service;

import com.example.clinic.dto.loginDto.LoginDto;
import com.example.clinic.dto.loginDto.RegisterDto;
import com.example.clinic.entity.User;
import com.example.clinic.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());

        userRepository.save(user);
        return "User Registered Successfully";
    }

    public String login(LoginDto dto){
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(()->new RuntimeException("User Not Found"));

        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            throw new RuntimeException("Wrong Password");
        }
        return "User Logged in successfully";
    }
}
