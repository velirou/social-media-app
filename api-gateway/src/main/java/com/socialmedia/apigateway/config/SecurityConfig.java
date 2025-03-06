package com.socialmedia.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/**").authenticated()  // Secure API endpoints with JWT
                        .pathMatchers("/oauth2/**").permitAll()  // Allow OAuth2 login endpoints
                        .anyExchange().denyAll()  // Deny everything else
                )
                .oauth2Login(withDefaults())  // Enable OAuth2 login for UI
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwkSetUri("http://localhost:8080/realms/social-media/protocol/openid-connect/certs"))
                );

        return http.build();
    }




    @Bean
    public ReactiveClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration registration = ClientRegistration
                .withRegistrationId("keycloak")
                .clientId("apigateway-client")
                .clientSecret("YrH1iZmCVT6LcZWNCftTLnbXUswdOgMz")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")                .scope("openid", "profile", "email")
                .authorizationUri("http://localhost:8080/realms/social-media/protocol/openid-connect/auth")
                .tokenUri("http://localhost:8080/realms/social-media/protocol/openid-connect/token")
                .userInfoUri("http://localhost:8080/realms/social-media/protocol/openid-connect/userinfo")
                .jwkSetUri("http://localhost:8080/realms/social-media/protocol/openid-connect/certs")
                .userNameAttributeName("sub")
                .clientName("Keycloak")
                .build();

        return new InMemoryReactiveClientRegistrationRepository(registration);
    }
}