package com.coding.internship.auth.service;

import com.coding.internship.auth.dto.*;
import com.coding.internship.security.jwt.JwtService;
import com.coding.internship.user.admin.model.Admin;
import com.coding.internship.user.admin.repository.AdminRepository;
import com.coding.internship.user.client.model.Client;
import com.coding.internship.user.client.repository.ClientRepository;
import com.coding.internship.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final UserRepository userRepository;

    public ClientRegisterResponse register(ClientRegisterRequest clientRegisterRequest){
        var client = Client.builder()
                .email(clientRegisterRequest.getEmail())
                .password(passwordEncoder.encode(clientRegisterRequest.getPassword()))
                .address(clientRegisterRequest.getAddress())
                .cin(clientRegisterRequest.getCin())
                .firstName(clientRegisterRequest.getFirstname())
                .lastName(clientRegisterRequest.getLastname())
                .phoneNumber(clientRegisterRequest.getPhoneNumber())
                .isStudent(clientRegisterRequest.getIsStudent())
                .build();
        Client savedClient= clientRepository.save(client);
        return ClientRegisterResponse.builder().email(client.getEmail()).firstName(client.getFirstName()).lastName(client.getLastName()).clientType(savedClient.getType()).build();



    }
    public AdminRegisterResponse registerAdmin(AdminRegisterRequest adminRegisterRequest){
        var admin = Admin.builder()
                .email(adminRegisterRequest.getEmail())
                .password(passwordEncoder.encode(adminRegisterRequest.getPassword()))
                .firstName(adminRegisterRequest.getFirstname())
                .lastName(adminRegisterRequest.getLastname())
                .department(adminRegisterRequest.getDepartment())
                .build();
        Admin savedAdmin= adminRepository.save(admin);
        return AdminRegisterResponse
                .builder()
                .firstname(savedAdmin.getFirstName())
                .lastname(savedAdmin.getLastName())
                .email(savedAdmin.getEmail())
                .department(savedAdmin.getDepartment())
                .adminRole(savedAdmin.getRole())
                .build();
    }

    public AuthResponse login(AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
