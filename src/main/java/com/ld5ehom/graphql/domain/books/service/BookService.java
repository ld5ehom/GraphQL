package com.ld5ehom.graphql.domain.books.service;

import com.ld5ehom.graphql.domain.books.entity.Book;
import com.ld5ehom.graphql.domain.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Provides application-level book operations used by graphql services
// graphql 서비스에서 사용하는 서적 도메인 애플리케이션 서비스
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Save a new book or update an existing one
    // 서적 정보를 저장하거나 기존 서적 정보를 업데이트하는 메소드
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Find a book by ID
    // ID로 특정 서적을 조회하는 메소드
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    // Retrieve all books
    // 모든 서적 목록을 조회하는 메소드
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // Find books by a specific author's name
    // 특정 저자의 이름으로 서적을 조회하는 메소드
    public List<Book> findByAuthorName(String authorName) {
        return bookRepository.findByAuthorsName(authorName);
    }

    // Delete a book by ID
    // ID로 특정 서적을 삭제하는 메소드
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
