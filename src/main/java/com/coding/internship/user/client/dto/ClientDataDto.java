package com.coding.internship.user.client.dto;

import com.coding.internship.user.dto.UserDataDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDataDto extends UserDataDto {
    private String address;
    private String phoneNumber;
    private String cin;
    private Boolean isStudent;
}
