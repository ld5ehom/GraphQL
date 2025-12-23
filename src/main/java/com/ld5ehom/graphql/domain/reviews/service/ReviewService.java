package com.ld5ehom.graphql.domain.reviews.service;

import com.ld5ehom.graphql.domain.reviews.entity.Review;
import com.ld5ehom.graphql.domain.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Provides application-level review operations used by graphql services
// graphql 서비스에서 사용하는 리뷰 도메인 애플리케이션 서비스
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Save a new review or update an existing one
    // 리뷰 정보를 저장하거나 기존 리뷰 정보를 업데이트하는 메소드
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    // Find a review by ID
    // ID로 특정 리뷰를 조회하는 메소드
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    // Find reviews for a specific book ordered by creation date (descending)
    // 특정 책의 리뷰를 작성일 기준 내림차순으로 조회하는 메소드
    public List<Review> findByBookId(Long bookId) {
        return reviewRepository.findByBookIdOrderByCreatedDateDesc(bookId);
    }

    // Delete a review by ID
    // ID로 특정 리뷰를 삭제하는 메소드
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}