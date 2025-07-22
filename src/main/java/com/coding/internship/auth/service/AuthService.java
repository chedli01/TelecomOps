package com.coding.internship.auth.service;

import com.coding.internship.auth.dto.AuthRequest;
import com.coding.internship.auth.dto.AuthResponse;
import com.coding.internship.auth.dto.RegisterRequest;
import com.coding.internship.auth.dto.RegisterResponse;
import com.coding.internship.security.jwt.JwtService;
import com.coding.internship.user.admin.repository.AdminRepository;
import com.coding.internship.user.client.model.Client;
import com.coding.internship.user.client.repository.ClientRepository;
import com.coding.internship.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest registerRequest){
        var client = Client.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .address(registerRequest.getAddress())
                .cin(registerRequest.getCin())
                .firstName(registerRequest.getFirstname())
                .lastName(registerRequest.getLastname())
                .phoneNumber(registerRequest.getPhoneNumber())
                .isStudent(registerRequest.getIsStudent())
                .build();
        clientRepository.save(client);
        return RegisterResponse.builder().email(client.getEmail()).firstName(client.getFirstName()).lastName(client.getLastName()).build();



    }

    public AuthResponse login(AuthRequest authRequest){
        return null;
    }
}
