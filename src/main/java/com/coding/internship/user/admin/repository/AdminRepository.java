package com.coding.internship.user.admin.repository;

import com.coding.internship.user.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
