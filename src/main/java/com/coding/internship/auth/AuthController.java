package com.coding.internship.auth;

import com.coding.internship.auth.dto.*;
import com.coding.internship.auth.service.AuthService;
import jakarta.validation.Valid;
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
    public ClientRegisterResponse register(@RequestBody @Valid ClientRegisterRequest clientRegisterRequest){
        return authService.register(clientRegisterRequest) ;
    }
    @PostMapping("/admin/register")
    public AdminRegisterResponse adminRegister(@RequestBody @Valid AdminRegisterRequest adminRegisterRequest){
        return authService.registerAdmin(adminRegisterRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid AuthRequest request){
        return authService.login(request) ;
    }

}
