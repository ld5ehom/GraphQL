package com.ld5ehom.graphql.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// Spring Security configuration for GraphQL authentication
// GraphQL 인증 처리를 위한 Spring Security 설정 클래스
@Configuration
public class SecurityConfig {

    // Configure HTTP security for GraphQL endpoints
    // GraphQL 엔드포인트 접근을 위한 보안 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for non-browser GraphQL clients
                // GraphQL 클라이언트 사용을 위해 CSRF 비활성화
                .csrf(csrf -> csrf.disable())

                // Require authentication for all requests
                // 모든 요청에 대해 인증 요구
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )

                // Enable HTTP Basic authentication
                // HTTP Basic 인증 활성화
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // In-memory user store for development and learning
    // 학습 및 개발용 인메모리 사용자 설정
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    // Password encoder for encoding user passwords
    // 사용자 비밀번호 암호화를 위한 PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
