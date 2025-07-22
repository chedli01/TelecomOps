package com.coding.internship.user.client.model;

import com.coding.internship.user.client.enums.ClientType;
import com.coding.internship.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Client extends User {
    private String address;
    private String phoneNumber;
    private String cin;
    private Boolean isStudent;
    @Enumerated(EnumType.STRING)
    private ClientType type;


}
