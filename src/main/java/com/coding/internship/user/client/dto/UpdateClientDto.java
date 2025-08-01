package com.coding.internship.user.client.dto;

import com.coding.internship.user.client.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateClientDto {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String cin;
    private Boolean isStudent;
    private ClientType clientType;
}
