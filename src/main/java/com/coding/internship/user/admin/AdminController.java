package com.coding.internship.user.admin;

import com.coding.internship.user.admin.model.Admin;
import com.coding.internship.user.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/{id}")
    public Admin findAdminById(@PathVariable Long id){
        return adminService.findAdminById(id);
    }
}
