package com.coding.internship.auth;

import com.coding.internship.auth.dto.AuthRequest;
import com.coding.internship.auth.dto.AuthResponse;
import com.coding.internship.auth.dto.ClientRegisterRequest;
import com.coding.internship.auth.dto.ClientRegisterResponse;
import com.coding.internship.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ClientRegisterResponse register(@RequestBody ClientRegisterRequest clientRegisterRequest){
        return authService.register(clientRegisterRequest) ;
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request){
        return authService.login(request) ;
    }
}
