package com.coding.internship.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDto {
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String email;
}
