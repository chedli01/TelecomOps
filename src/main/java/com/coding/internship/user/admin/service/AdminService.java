package com.coding.internship.user.admin.service;

import com.coding.internship.user.admin.dto.AdminDataDto;
import com.coding.internship.user.admin.mapper.AdminMapper;
import com.coding.internship.user.admin.model.Admin;
import com.coding.internship.user.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminDataDto findAdminById(Long id){
        Admin admin=adminRepository.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
        return adminMapper.mapToDto(admin) ;
    }

    public Admin createAdmin(Admin admin){
        return adminRepository.save(admin);
    }

}
