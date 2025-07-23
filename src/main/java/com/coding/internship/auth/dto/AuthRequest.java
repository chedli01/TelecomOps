package com.coding.internship.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @Email(message = "this should be a valid email")
    @NotBlank(message = "this field is required")
    private String email;
    @NotBlank(message = "this field is required")
    private String password;
}
