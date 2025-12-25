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

    // Configure HTTP security for GraphQL endpoints and documentation UIs
    // GraphQL 엔드포인트 및 문서화 UI 접근을 위한 보안 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for GraphQL clients and static UI pages
                // GraphQL 클라이언트 및 정적 UI 페이지 사용을 위해 CSRF 비활성화
                .csrf(csrf -> csrf.disable())

                // Allow unauthenticated access to GraphiQL / Playground and the GraphQL endpoint
                // GraphiQL / Playground 및 GraphQL 엔드포인트는 인증 없이 접근 허용
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/playground.html",     // GraphQL Playground UI
                                "/graphiql",            // GraphiQL UI
                                "/graphql"              // GraphQL API endpoint
                        ).permitAll()
                        // Require authentication for everything else
                        // 그 외 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                )

                // Enable HTTP Basic authentication for protected endpoints
                // 보호된 엔드포인트에 대해 HTTP Basic 인증 활성화
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // In-memory user store for development and learning
    // 학습 및 개발용 인메모리 사용자 설정
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                // Default username for local development
                // 로컬 개발용 기본 사용자명
                .username("user")
                // Encode password using BCrypt
                // BCrypt로 비밀번호 인코딩
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
