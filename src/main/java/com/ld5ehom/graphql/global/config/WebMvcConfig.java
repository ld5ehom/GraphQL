package com.ld5ehom.graphql.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Web MVC configuration for serving static GraphQL tooling resources
// GraphQL 문서화 및 개발 도구(Voyager, Playground)를 위한 정적 리소스 설정
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // GraphQL Voyager static resource mapping
        // /voyager/** 경로로 GraphQL Voyager UI 제공
        registry.addResourceHandler("/voyager/**")
                .addResourceLocations("classpath:/static/voyager/");

        // GraphQL Playground static resource mapping
        // /playground/** 경로로 GraphQL Playground UI 제공
        registry.addResourceHandler("/playground/**")
                .addResourceLocations("classpath:/static/playground/");
    }
}
