package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for API
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/auth/login",
                    "/api/auth/forgot-password",
                    "/api/auth/verify-otp",
                    "/api/auth/change-password",
                    "/api/auth/logout",
                    "/api/public/**"
                ).permitAll() // Public endpoints
                .anyRequest().authenticated() // Everything else requires login
            )
            .httpBasic(basic -> basic.disable()) // disable default basic login popup
            .formLogin(form -> form.disable());  // disable form login

        return http.build();
    }
}
