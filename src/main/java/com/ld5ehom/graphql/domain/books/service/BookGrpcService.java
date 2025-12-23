package com.ld5ehom.graphql.domain.books.service;

import bookstore.BookServicegraphql;
import bookstore.Bookstore;
import com.ld5ehom.graphql.domain.books.entity.Book;
import com.ld5ehom.graphql.global.interceptor.AccessLoggingInterceptor;
import com.ld5ehom.graphql.global.interceptor.BasicAuthInterceptor;
import com.ld5ehom.graphql.global.utils.TimestampConverter;
import io.graphql.stub.StreamObserver;
import net.devh.boot.graphql.server.service.graphqlService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// Exposes book-related operations via graphql endpoints
// graphql를 통해 서적 관련 기능을 제공하는 진입 서비스
@graphqlService(interceptors = { AccessLoggingInterceptor.class,BasicAuthInterceptor.class })
public class BookgraphqlService extends BookServicegraphql.BookServiceImplBase {

    private final BookService bookService;

    @Autowired
    public BookgraphqlService(BookService bookService) {
        this.bookService = bookService;
    }

    // Handle graphql request to create a new book
    // graphql 서적 생성 요청 처리
    @Override
    public void addBook(Bookstore.AddBookRequest request, StreamObserver<Bookstore.Book> responseObserver) {
        Book newBook = new Book();
        newBook.setTitle(request.getTitle());
        newBook.setPublisher(request.getPublisher());
        newBook.setPublishedDate(TimestampConverter.fromProto(request.getPublishedDate()));

        Book savedBook = bookService.saveBook(newBook);

        responseObserver.onNext(
                Bookstore.Book.newBuilder()
                        .setTitle(savedBook.getTitle())
                        .setPublishedDate(TimestampConverter.toProto(savedBook.getPublishedDate()))
                        .setPublisher(savedBook.getPublisher())
                        .build()
        );
        responseObserver.onCompleted();
    }

    // Handle graphql request to retrieve book details
    // graphql 서적 상세 조회 요청 처리
    @Override
    public void getBookDetails(Bookstore.GetBookDetailsRequest request, StreamObserver<Bookstore.Book> responseObserver) {
        Bookstore.Book bookDetail = bookService.findById(request.getBookId())
                .map(book -> Bookstore.Book.newBuilder()
                        .setTitle(book.getTitle())
                        .setPublishedDate(TimestampConverter.toProto(book.getPublishedDate()))
                        .setPublisher(book.getPublisher())
                        .build())
                .orElseThrow(); // Error

        responseObserver.onNext(bookDetail);
        responseObserver.onCompleted();
    }

    // Handle graphql request to stream all books
    // graphql 전체 서적 목록 조회 요청 처리
    @Override
    public void listBooks(Bookstore.ListBooksRequest request, StreamObserver<Bookstore.Book> responseObserver) {
        List<Book> books = bookService.findAll();

        for (Book book : books) {
            responseObserver.onNext(
                    Bookstore.Book.newBuilder()
                            .setTitle(book.getTitle())
                            .setPublishedDate(TimestampConverter.toProto(book.getPublishedDate()))
                            .setPublisher(book.getPublisher())
                            .build()
            );
        }

        responseObserver.onCompleted();
    }

    // Handle graphql request to search books by author
    // graphql 저자 기준 서적 검색 요청 처리
    @Override
    public void searchBooksByAuthor(Bookstore.SearchBooksByAuthorRequest request, StreamObserver<Bookstore.Book> responseObserver) {
        List<Book> books = bookService.findByAuthorName(request.getAuthorName());

        for (Book book : books) {
            responseObserver.onNext(
                    Bookstore.Book.newBuilder()
                            .setTitle(book.getTitle())
                            .setPublishedDate(TimestampConverter.toProto(book.getPublishedDate()))
                            .setPublisher(book.getPublisher())
                            .build()
            );
        }

        responseObserver.onCompleted();
    }
}
