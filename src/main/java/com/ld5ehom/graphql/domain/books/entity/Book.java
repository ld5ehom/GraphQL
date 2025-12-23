package com.ld5ehom.graphql.domain.books.entity;

import com.ld5ehom.graphql.domain.authors.entity.Author;
import com.ld5ehom.graphql.domain.reviews.entity.Review;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// Represents the Book domain entity used by graphql services
// graphql 서비스에서 사용하는 Book 도메인 엔티티
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String publisher;

    @Column(name = "published_date")
    @Temporal(TemporalType.DATE)
    private Date publishedDate;

    // Many-to-Many relationship with Author entity.
    // A book can have multiple authors, and an author can write multiple books.
    // This side owns the relationship, using a join table "books_authors".
    // Author 엔티티와의 다대다 관계를 정의.
    // 한 권의 책은 여러 저자를 가질 수 있고, 한 저자도 여러 책을 쓸 수 있음.
    // 이쪽(Book)이 연관관계 주인이며, "books_authors" 조인 테이블을 사용.
    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
//    @JsonManagedReference
    private Set<Author> authors = new HashSet<>();

    // One-to-Many relationship with Review entity.
    // A book can have multiple reviews, and each review belongs to a single book.
    // 리뷰 엔티티와의 일대다 관계를 정의.
    // 한 권의 책은 여러 리뷰를 가질 수 있으며, 각 리뷰는 하나의 책에 속함.
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
    private Set<Review> reviews = new HashSet<>();
}