package com.coding.internship.auth.dto;

import com.coding.internship.user.client.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegisterResponse {
    private String firstName;
    private String lastName;
    private String email;
    private ClientType clientType;
    private LocalDateTime createdAt;
}

