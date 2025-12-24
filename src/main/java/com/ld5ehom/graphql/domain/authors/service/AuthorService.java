package com.ld5ehom.graphql.domain.authors.service;

import com.ld5ehom.graphql.domain.authors.entity.Author;
import com.ld5ehom.graphql.domain.authors.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Provides application-level author operations used by GraphQL services
// GraphQL 서비스에서 사용하는 Author 도메인 애플리케이션 서비스
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Save a new author or update an existing one
    // Author 엔티티를 저장하거나 수정
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Find an author by ID
    // ID를 기준으로 단일 Author 조회
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    // Find multiple authors by their IDs
    // 여러 Author를 ID 목록 기준으로 조회
    public List<Author> findAllById(List<Long> ids) {
        return authorRepository.findAllById(ids);
    }

    // Retrieve all authors
    // 저장된 모든 Author 조회
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
