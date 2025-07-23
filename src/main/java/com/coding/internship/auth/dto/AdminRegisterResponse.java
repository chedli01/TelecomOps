package com.coding.internship.auth.dto;

import com.coding.internship.user.admin.enums.DepartmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRegisterResponse {
    private String firstname;
    private String lastname;
    private String email;
    private DepartmentType department;
}
