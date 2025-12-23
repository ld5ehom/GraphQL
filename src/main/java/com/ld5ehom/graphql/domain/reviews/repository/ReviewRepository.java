package com.ld5ehom.graphql.domain.reviews.repository;

import com.ld5ehom.graphql.domain.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Provides persistence operations for Review entities used by graphql services
// graphql 서비스에서 사용하는 Review 엔티티 영속성 접근 레이어
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Find reviews for a specific book and order them by creation date in descending order
    // 특정 책의 리뷰를 작성일 기준 내림차순으로 조회하는 메소드
    List<Review> findByBookIdOrderByCreatedDateDesc(Long bookId);
}