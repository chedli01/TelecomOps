package com.coding.internship.auth.dto;

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
public class ClientRegisterRequest {
    @NotBlank(message = "Firstname should not be blank")
    private String firstname;
    @NotBlank(message = "Lastname should not be blank")
    private String lastname;
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    @NotBlank(message = "this field is required")
    private String address;
    @Size(min = 8,max = 8,message = "phone-number should be composed of 8 numbers ")
    private String phoneNumber;
    @Size(min = 8,max = 8,message = "cin should be composed of 8 numbers ")
    private String cin;
    private Boolean isStudent;

}
