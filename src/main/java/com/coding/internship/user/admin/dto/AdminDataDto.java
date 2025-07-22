package com.coding.internship.user.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDataDto{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String role;
}
