package com.cityvet.dogadoption.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // PUBLIC
                        .requestMatchers("/health", "/auth/register").permitAll()

                        // ✅ IMPORTANT: put this BEFORE POST /dogs/**
                        // Adoption request creation: any authenticated user
                        .requestMatchers(HttpMethod.POST, "/dogs/*/requests").authenticated()
                        // (or if you want role-based)
                        // .requestMatchers(HttpMethod.POST, "/dogs/*/requests").hasAnyRole("USER", "ADMIN")

                        // DOGS
                        .requestMatchers(HttpMethod.GET, "/dogs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/dogs/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/dogs/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/dogs/**").hasRole("ADMIN")

                        // ADMIN only
                        .requestMatchers("/requests/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}