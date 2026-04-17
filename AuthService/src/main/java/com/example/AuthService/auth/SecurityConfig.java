package com.example.AuthService.auth;

import com.example.AuthService.repository.UserRepository;
import com.example.AuthService.service.UserDetailsServiceImp;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableMethodSecurity
@Data
public class SecurityConfig {
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Bean
    @Autowired
    public UserDetailsService userDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserDetailsServiceImp(userRepository, passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable).cors(CorsConfigurer::disable)
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers()
                )
    }
}
