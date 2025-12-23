package com.ld5ehom.graphql.domain.reviews.service;

import bookstore.Bookstore;
import bookstore.ReviewServicegraphql;
import com.ld5ehom.graphql.domain.reviews.entity.Review;
import com.ld5ehom.graphql.global.interceptor.AccessLoggingInterceptor;
import com.ld5ehom.graphql.global.interceptor.BasicAuthInterceptor;
import com.ld5ehom.graphql.global.utils.TimestampConverter;
import io.graphql.stub.StreamObserver;
import net.devh.boot.graphql.server.service.graphqlService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// Exposes review-related operations via graphql endpoints
// graphql를 통해 리뷰 관련 기능을 제공하는 진입 서비스
@graphqlService(interceptors = { AccessLoggingInterceptor.class, BasicAuthInterceptor.class })
public class ReviewgraphqlService extends ReviewServicegraphql.ReviewServiceImplBase {

    private final ReviewService reviewService;

    @Autowired
    public ReviewgraphqlService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Handle graphql request to retrieve reviews for a book
    // graphql 책 기준 리뷰 조회 요청 처리
    @Override
    public void getReviews(Bookstore.GetReviewsRequest request, StreamObserver<Bookstore.Review> responseObserver) {
        List<Review> reviews = reviewService.findByBookId(request.getBookId());

        for (Review review : reviews) {
            responseObserver.onNext(
                    Bookstore.Review.newBuilder()
                            .setId(review.getId())
                            .setBookId(review.getBook().getId())
                            .setRating(review.getRating())
                            .setCreatedDate(TimestampConverter.toProto(review.getCreatedDate()))
                            .setContent(review.getContent())
                            .build()
            );
        }

        responseObserver.onCompleted();
    }
}
