package com.ld5ehom.graphql.domain.version.controller;

import com.ld5ehom.graphql.domain.version.entity.Version;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

// GraphQL controller responsible for handling Version-related queries
// Version 관련 GraphQL Query를 처리하는 컨트롤러
//
// This controller acts as a GraphQL resolver entry point,
// mapping schema-defined queries to backend execution logic.
// 이 컨트롤러는 스키마에 정의된 Query를 실제 백엔드 실행 로직과 연결하는 역할을 한다.
@Controller
public class VersionController {

    // Maps the GraphQL Query "getVersion" to this resolver method
    // GraphQL 스키마에 정의된 getVersion Query를 이 메서드와 매핑한다
    //
    // @QueryMapping indicates that this method is invoked during GraphQL query execution,
    // not through a traditional REST endpoint.
    // @QueryMapping은 REST 엔드포인트가 아니라 GraphQL Query 실행 시 호출됨을 의미한다.
    @QueryMapping
    public Version getVersion() {
        // Delegate Version creation to the domain entity
        // Version 생성 책임을 도메인 엔티티에 위임한다
        return Version.getVersion();
    }
}
