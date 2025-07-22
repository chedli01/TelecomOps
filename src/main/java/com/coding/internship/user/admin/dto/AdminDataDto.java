package com.coding.internship.user.admin.dto;

import com.coding.internship.user.dto.UserDataDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDataDto extends UserDataDto {
    private String department;
    private String role;
}
