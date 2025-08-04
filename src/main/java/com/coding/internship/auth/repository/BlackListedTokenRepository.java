package com.coding.internship.auth.repository;

import com.coding.internship.auth.model.BlackListedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListedTokenRepository extends JpaRepository<BlackListedToken, Long> {
    public BlackListedToken findByToken(String token);
}
