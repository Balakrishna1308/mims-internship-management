package com.mfa.mims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/api/users/**").hasRole("ADMIN")  // Restrict /api/users/** to ADMIN role
                                .anyRequest().authenticated()  // All other requests need authentication
                )
                .httpBasic();  // Enables basic HTTP authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Creates an in-memory user with the "ADMIN" role
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123")  // {noop} disables password encoding (plaintext password)
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}
