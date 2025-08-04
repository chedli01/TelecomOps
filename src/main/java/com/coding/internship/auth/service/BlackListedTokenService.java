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
    public boolean isTokenBlackListed(String token){
        BlackListedToken blackListedToken = getToken(token);
        if(blackListedToken!=null){
            System.out.println("token is blacklisted");
            return true;
        }
        System.out.println("token is not blacklisted");

        return false;
    }

}
