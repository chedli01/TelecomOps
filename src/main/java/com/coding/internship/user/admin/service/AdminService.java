package com.coding.internship.user.admin.service;

import com.coding.internship.user.admin.model.Admin;
import com.coding.internship.user.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public Admin findAdminById(Long id){
        return adminRepository.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }

}
