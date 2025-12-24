package com.ld5ehom.graphql.domain.books.controller;

import com.ld5ehom.graphql.domain.authors.service.AuthorService;
import com.ld5ehom.graphql.domain.books.entity.Book;
import com.ld5ehom.graphql.domain.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// GraphQL controller handling Book-related queries and mutations
// Book 도메인과 관련된 GraphQL Query 및 Mutation을 처리하는 컨트롤러
@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    // Create a Book with associated authors
    // Author 연관관계를 포함해 Book을 생성하는 Mutation
    @MutationMapping
    public Book addBook(
            @Argument String title,
            @Argument String publisher,
            @Argument LocalDate publishedDate,
            @Argument List<Long> authorIds
    ) {
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setPublisher(publisher);
        newBook.setPublishedDate(publishedDate);
        newBook.setAuthors(new HashSet<>(authorService.findAllById(authorIds)));

        return bookService.saveBook(newBook);
    }

    // Retrieve a Book by its ID
    // ID를 기준으로 단일 Book을 조회하는 Query
    @QueryMapping
    public Book getBookById(@Argument Long id) {
        return bookService.findById(id).orElseThrow();
    }

    // Retrieve all books
    // 저장된 모든 Book을 조회하는 Query
    @QueryMapping
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    // Retrieve books filtered by author name
    // 저자 이름을 기준으로 Book 목록을 조회하는 Query
    @QueryMapping
    public List<Book> getBooksByAuthorName(@Argument String authorName) {
        return bookService.findByAuthorName(authorName);
    }

    // Delete a Book by its ID
    // ID를 기준으로 Book을 삭제하는 Mutation
    @MutationMapping
    public Map<String, Boolean> deleteBook(@Argument Long id) {
        bookService.deleteById(id);

        Map<String, Boolean> result = new HashMap<>();
        result.put("Success", true);
        return result;
    }
}
