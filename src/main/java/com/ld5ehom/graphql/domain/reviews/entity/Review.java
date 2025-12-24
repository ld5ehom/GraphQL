package com.ld5ehom.graphql.domain.reviews.entity;

import com.ld5ehom.graphql.domain.books.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Review belongs to a single Book
    // 각 Review는 하나의 Book에 속함
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    // Review content
    // 리뷰 내용
    @Column(nullable = false)
    private String content;

    // Rating score
    // 평점
    private float rating;

    // Review creation timestamp with timezone
    // 타임존을 포함한 리뷰 생성 시각
    @Column(name = "created_date")
    private OffsetDateTime createdDate;
}
