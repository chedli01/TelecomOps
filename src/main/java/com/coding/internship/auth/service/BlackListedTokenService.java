package com.coding.internship.auth.service;

import com.coding.internship.auth.model.BlackListedToken;
import com.coding.internship.auth.repository.BlackListedTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListedTokenService {
    private final BlackListedTokenRepository blackListedTokenRepository;

    public BlackListedToken getToken(String token) {
        return blackListedTokenRepository.findByToken(token);
    }

}
