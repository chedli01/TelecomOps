package com.coding.internship.user.admin.model;

import com.coding.internship.user.admin.enums.AdminRole;
import com.coding.internship.user.admin.enums.DepartmentType;
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
@Table(name = "admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Admin extends User {
    @Enumerated(EnumType.STRING)
    private DepartmentType department;
    @Enumerated(EnumType.STRING)
    private AdminRole role;
}
