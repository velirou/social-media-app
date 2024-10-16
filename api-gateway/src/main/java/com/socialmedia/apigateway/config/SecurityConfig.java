package com.socialmedia.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Define which endpoints require authentication and authorization
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/users/public", "/api/posts/public").permitAll()  // Allow public endpoints
                        .anyRequest().authenticated()  // All other endpoints require authentication
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> {
                    jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter());
                }));
        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // Custom JWT converter can be added here if needed
        return converter;
    }
}
