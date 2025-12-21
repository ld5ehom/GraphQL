package com.ld5ehom.graphql.domain.version.entity;

import java.util.Date;

// Represents application version information exposed through GraphQL
// GraphQL을 통해 노출되는 애플리케이션 버전 정보를 표현하는 엔티티
//
// This entity is designed as an immutable data carrier using Java record,
// which aligns well with GraphQL's value-oriented response model.
// GraphQL 응답은 값 중심 구조이기 때문에, 불변 객체인 record 구조가 잘 맞는다.
public record Version(String name, Date releaseDate) {

    // Provides a static factory method for returning a sample Version instance
    // 테스트 및 초기 GraphQL Query 연결을 위한 샘플 Version 객체를 반환하는 팩토리 메서드
    //
    // This method is intentionally static to decouple Version creation logic
    // from controllers or resolvers during early GraphQL setup.
    // 초기 GraphQL 구성 단계에서 컨트롤러나 리졸버와 생성 로직을 분리하기 위해 static으로 정의한다.
    public static Version getVersion() {
        return new Version("1.0.0", new Date());
    }
}
