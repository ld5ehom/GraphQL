package com.ld5ehom.graphql.domain.books.entity;

import com.ld5ehom.graphql.domain.authors.entity.Author;
import com.ld5ehom.graphql.domain.reviews.entity.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Book title
    @Column(nullable = false)
    private String title;

    // Publisher name
    private String publisher;

    // Publication date (date-only, no time component)
    // 출판일 (시간 정보 없이 날짜만 사용)
    @Column(name = "published_date")
    private LocalDate publishedDate;

    // Authors associated with this book (many-to-many)
    // Book과 Author 간 다대다 연관관계
    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    // Reviews written for this book
    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Review> reviews = new HashSet<>();
}
