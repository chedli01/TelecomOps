package com.coding.internship.user.client.model;

import com.coding.internship.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {
    private String address;
    private String phoneNumber;
    private String cin;
    private Boolean isStudent;
    private String role;


}
