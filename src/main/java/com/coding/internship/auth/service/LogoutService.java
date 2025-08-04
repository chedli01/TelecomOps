package com.coding.internship.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final BlackListedTokenService blackListedTokenService;



    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        System.out.println("logout");
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//            System.out.println("token is null");
            return;
        }
        jwt = authHeader.substring(7);

        if(jwt!=null){
            blackListedTokenService.saveToken(jwt);
        }

    }
}
