package com.ld5ehom.graphql.global.directive;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

// GraphQL authentication directive for field-level authorization
// GraphQL 필드 단위 인증 처리를 위한 커스텀 디렉티브
@Component
public class AuthenticationDirective implements SchemaDirectiveWiring {

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        GraphQLFieldDefinition fieldDefinition = environment.getFieldDefinition();
        GraphQLObjectType parentType = (GraphQLObjectType) environment.getFieldsContainer();

        // Retrieve the original DataFetcher for the field
        // 해당 필드에 등록된 기존 DataFetcher 조회
        DataFetcher<?> originalDataFetcher =
                environment.getCodeRegistry().getDataFetcher(parentType, fieldDefinition);

        // Wrap the DataFetcher with authentication check
        // 인증 검사를 추가한 DataFetcher로 감싸기
        DataFetcher<?> authDataFetcher = (DataFetchingEnvironment dataFetchingEnvironment) -> {

            // Get authentication from Spring Security context
            // Spring Security 컨텍스트에서 현재 인증 정보 조회
            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            // Verify authentication status
            // 인증 여부 및 익명 사용자 여부 확인
            if (authentication == null
                    || !authentication.isAuthenticated()
                    || "anonymousUser".equals(authentication.getPrincipal())) {

                // Reject unauthenticated access
                // 인증되지 않은 경우 접근 차단
                throw new IllegalAccessException("Access Denied - Authentication required");
            }

            // Delegate to the original DataFetcher when authenticated
            // 인증 성공 시 기존 DataFetcher 실행
            return originalDataFetcher.get(dataFetchingEnvironment);
        };

        // Register the secured DataFetcher
        // 인증 로직이 추가된 DataFetcher를 GraphQL 런타임에 등록
        environment.getCodeRegistry()
                .dataFetcher(parentType, fieldDefinition, authDataFetcher);

        return fieldDefinition;
    }
}
