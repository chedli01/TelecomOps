package com.coding.internship.user.admin.model;

import com.coding.internship.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Admin extends User {
    private String department;
    private String role;
}
