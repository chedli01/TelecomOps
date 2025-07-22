package com.coding.internship.auth.service;

import com.coding.internship.auth.dto.AuthRequest;
import com.coding.internship.auth.dto.AuthResponse;
import com.coding.internship.auth.dto.RegisterRequest;
import com.coding.internship.auth.dto.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public RegisterResponse register(RegisterRequest registerRequest){

        return null;



    }

    public AuthResponse login(AuthRequest authRequest){
        return null;
    }
}
