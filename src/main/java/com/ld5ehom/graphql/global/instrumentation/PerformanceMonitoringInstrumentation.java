package com.ld5ehom.graphql.global.instrumentation;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

// Interceptor that measures GraphQL operation execution time
// GraphQL Operation(Query/Mutation) 실행 시간을 측정하는 인터셉터
@Slf4j
@Component
@NullMarked
public class PerformanceMonitoringInstrumentation implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(
            WebGraphQlRequest request,
            Chain chain
    ) {
        // Capture the start time for this request
        // 현재 요청의 시작 시각 기록
        Instant start = Instant.now();

        return chain.next(request)
                .doFinally(signalType -> {
                    // Log the elapsed time after the request finishes
                    // 요청이 끝난 뒤 총 수행 시간을 계산하여 로그 출력
                    long elapsedMs = Duration.between(start, Instant.now()).toMillis();

                    log.info(
                            "GraphQL request [{}] finished in {} ms",
                            request.getId(),
                            elapsedMs
                    );
                });
    }
}
