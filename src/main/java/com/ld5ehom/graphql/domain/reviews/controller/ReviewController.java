package com.ld5ehom.graphql.domain.reviews.controller;

import com.ld5ehom.graphql.domain.books.service.BookService;
import com.ld5ehom.graphql.domain.reviews.entity.Review;
import com.ld5ehom.graphql.domain.reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// GraphQL controller handling Review-related queries and mutations
// Review 도메인과 관련된 GraphQL Query 및 Mutation을 처리하는 컨트롤러
@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;

    @Autowired
    public ReviewController(ReviewService reviewService, BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }

    // Create a Review for a specific Book
    // 특정 Book에 대한 Review를 생성하는 Mutation
    @MutationMapping
    public Review addReview(
            @Argument Long bookId,
            @Argument String content,
            @Argument Float rating
    ) {
        Review newReview = new Review();
        newReview.setBook(bookService.findById(bookId).orElseThrow());
        newReview.setContent(content);
        newReview.setRating(rating);
        newReview.setCreatedDate(OffsetDateTime.now());

        return reviewService.saveReview(newReview);
    }

    // Retrieve reviews by Book ID
    // Book ID를 기준으로 Review 목록을 조회하는 Query
    @QueryMapping
    public List<Review> getReviewsByBookId(@Argument Long id) {
        return reviewService.findByBookId(id);
    }

    // Delete a Review by its ID
    // ID를 기준으로 Review를 삭제하는 Mutation
    @MutationMapping
    public Map<String, Boolean> deleteReviewById(@Argument Long reviewId) {
        reviewService.deleteById(reviewId);

        Map<String, Boolean> result = new HashMap<>();
        result.put("success", true);
        return result;
    }
}
