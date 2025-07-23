package com.coding.internship.auth;

import com.coding.internship.auth.dto.*;
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

    @PostMapping("/client/register")
    public ClientRegisterResponse register(@RequestBody ClientRegisterRequest clientRegisterRequest){
        return authService.register(clientRegisterRequest) ;
    }
    @PostMapping("/admin/register")
    public AdminRegisterResponse adminRegister(@RequestBody AdminRegisterRequest adminRegisterRequest){
        return authService.registerAdmin(adminRegisterRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request){
        return authService.login(request) ;
    }
}
