package com.ld5ehom.graphql.global.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

// GraphQL runtime configuration for custom scalars and directives
// GraphQL 실행 시 사용할 커스텀 스칼라 및 디렉티브를 등록하는 설정 클래스
@Configuration
public class GraphqlConfig {

    // Register custom scalar types and authentication directive
    // 커스텀 스칼라(Date, DateTime, Long)와 인증 디렉티브를 GraphQL 런타임에 등록
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                // Date scalar for date-only values
                // 날짜 전용 스칼라
                .scalar(ExtendedScalars.Date)

                // DateTime scalar with time and timezone support
                // 시간 및 타임존을 포함한 DateTime 스칼라
                .scalar(ExtendedScalars.DateTime)

                // Long scalar for large integer values
                // Long 타입 정수 값을 위한 스칼라
                .scalar(ExtendedScalars.GraphQLLong);

    }
}
