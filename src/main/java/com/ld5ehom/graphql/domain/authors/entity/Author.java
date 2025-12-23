package com.ld5ehom.graphql.domain.authors.entity;

import com.ld5ehom.graphql.domain.books.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Many-to-Many relationship with Book entity.
    // One author can write multiple books, and one book can have multiple authors.
    // "mappedBy = authors" indicates that the Book entity owns the relationship.
    // 책과 Book 엔티티 간의 다대다 관계를 정의.
    // 한 명의 저자가 여러 책을 쓸 수 있고, 한 권의 책도 여러 저자와 연결될 수 있음.
    // "mappedBy = authors" 는 연관관계의 주인이 Book 엔티티임을 의미.
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

}