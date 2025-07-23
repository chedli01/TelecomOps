package com.coding.internship.user.admin.model;

import com.coding.internship.user.admin.enums.AdminRole;
import com.coding.internship.user.admin.enums.DepartmentType;
import com.coding.internship.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

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
    @Builder.Default
    private AdminRole role =  AdminRole.ADMIN;
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}
