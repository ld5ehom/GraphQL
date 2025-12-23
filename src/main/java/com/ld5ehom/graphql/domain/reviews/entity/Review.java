package com.ld5ehom.graphql.domain.reviews.entity;

import com.ld5ehom.graphql.domain.books.entity.Book;
//import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

// Represents the Review domain entity used by graphql services
// graphql 서비스에서 사용하는 Review 도메인 엔티티
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One relationship with Book entity.
    // Each review belongs to a single book, but a book can have multiple reviews.
    // Book 엔티티와의 다대일 관계를 정의.
    // 각 리뷰는 하나의 책에 속하며, 한 권의 책은 여러 리뷰를 가질 수 있음.
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
//    @JsonBackReference
    private Book book;

    @Column(nullable = false)
    private String content;

    private float rating;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
}