package com.coding.internship.auth.dto;

import com.coding.internship.user.admin.enums.DepartmentType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegisterRequest {
    @NotBlank(message = "Firstname should not be blank")
    private String firstname;
    @NotBlank(message = "Lastname should not be blank")
    private String lastname;
    @Email(message = "Email should be valid")
    @NotBlank(message = "field email is required")
    private String email;
    @Size(min = 6, message = "Password must be at least 6 characters")
    @NotBlank(message = "field password is required")
    private String password;
    private DepartmentType department;

}
