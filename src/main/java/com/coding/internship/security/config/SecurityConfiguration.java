package com.coding.internship.security.config;

import com.coding.internship.auth.service.LogoutService;
import com.coding.internship.security.jwt.JwtAuthenticationFilter;
import com.coding.internship.user.admin.enums.AdminRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URL = {"/api/auth/**",
            "/api/sms/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
    };
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutService logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL).permitAll()
                                .requestMatchers("/api/admin/*").hasRole(AdminRole.SUPER_ADMIN.name())
                                .requestMatchers(HttpMethod.PUT,"/api/products/**").hasAnyRole(AdminRole.ADMIN.name(),AdminRole.SUPER_ADMIN.name())
                                .requestMatchers(HttpMethod.POST,"/api/products/**").hasAnyRole(AdminRole.ADMIN.name(),AdminRole.SUPER_ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE,"/api/products/**").hasRole(AdminRole.SUPER_ADMIN.name())
                                .requestMatchers(HttpMethod.PUT,"/api/plan/**").hasAnyRole(AdminRole.ADMIN.name(),AdminRole.SUPER_ADMIN.name())
                                .requestMatchers(HttpMethod.POST,"/api/plan/**").hasAnyRole(AdminRole.ADMIN.name(),AdminRole.SUPER_ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE,"/api/plan/**").hasRole(AdminRole.SUPER_ADMIN.name())
                                .anyRequest()
                                .authenticated()


                )

                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> {
                                    SecurityContextHolder.clearContext();
                                    response.setStatus(HttpStatus.OK.value());
                                    response.getWriter().write("{\"message\":\"Logout successful\"}");
                                })
                                .invalidateHttpSession(true)
                )
        ;

        return http.build();
    }

}
