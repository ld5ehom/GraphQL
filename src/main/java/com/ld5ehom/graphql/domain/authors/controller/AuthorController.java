package com.ld5ehom.graphql.domain.authors.controller;

import com.ld5ehom.graphql.domain.authors.entity.Author;
import com.ld5ehom.graphql.domain.authors.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

// GraphQL controller responsible for Author-related queries and mutations
// Author 도메인과 관련된 GraphQL Query 및 Mutation을 처리하는 컨트롤러
//
// This controller acts as a GraphQL resolver layer, delegating actual business logic to the AuthorService.
// 이 컨트롤러는 GraphQL 리졸버 역할을 하며, 실제 비즈니스 로직은 AuthorService에 위임한다.
@Controller
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Resolve the GraphQL query "getAllAuthors"
    // This method retrieves all authors from the database through the service layer.
    // GraphQL 스키마에 정의된 getAllAuthors Query를 처리
    // 서비스 계층을 통해 데이터베이스에 저장된 모든 Author를 조회한다.
    @QueryMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    // Resolve the GraphQL mutation "addAuthor"
    // This mutation creates a new Author entity and persists it.
    // GraphQL 스키마에 정의된 addAuthor Mutation을 처리
    // 새로운 Author 엔티티를 생성하고 데이터베이스에 저장한다.
    @MutationMapping
    public Author addAuthor(@Argument String authorName) {
        Author author = new Author();
        author.setName(authorName);

        return authorService.saveAuthor(author);
    }

    // Resolve the GraphQL query "getAuthorById"
    // This method fetches a single Author by its ID.
    // GraphQL 스키마에 정의된 getAuthorById Query를 처리
    // ID를 기준으로 특정 Author를 조회한다.
    @QueryMapping
    public Author getAuthorById(@Argument Long id) {
        return authorService.findById(id).orElseThrow();
    }
}
