package com.socialmedia.postservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/posts/public").permitAll()  // Allow public endpoints
                        .anyRequest().authenticated()  // All other endpoints require authentication
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> {
                    jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter());
                }))
                // Add custom filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(apiGatewayRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // Custom filter to allow requests only if they contain the correct header from API Gateway
    @Bean
    public ApiGatewayRequestFilter apiGatewayRequestFilter() {
        return new ApiGatewayRequestFilter();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // Custom JWT converter can be added here if needed
        return converter;
    }

    // Inner class for custom filter to check the presence of a specific header from the API Gateway
    public static class ApiGatewayRequestFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            // Check if the request contains the custom header added by the API Gateway
            String gatewayHeader = request.getHeader("X-Forwarded-By");

            if (gatewayHeader == null || !gatewayHeader.equals("ApiGateway")) {
                // If the header is missing or invalid, reject the request with 403 Forbidden
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            // Proceed with the request if the header is valid
            filterChain.doFilter(request, response);
        }
    }
}
