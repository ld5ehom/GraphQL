-- Authors (UCLA Professors)
INSERT INTO authors (id, name) VALUES (1000, 'Judea Pearl');
INSERT INTO authors (id, name) VALUES (2000, 'Leonard Kleinrock');

-- Books
INSERT INTO books (id, title, publisher, published_date)
VALUES (1000, 'The Book of Why', 'Basic Books', '2018-05-15');

INSERT INTO books (id, title, publisher, published_date)
VALUES (2000, 'Queueing Systems, Volume I: Theory', 'Wiley-Interscience', '1975-01-01');

-- Reviews (OffsetDateTime / DateTime scalar compatible)
INSERT INTO reviews (id, book_id, content, rating, created_date)
VALUES (
           1000,
           1000,
           'A foundational work on causal reasoning with clear real-world implications.',
           5.0,
           '2024-11-15T10:30:00Z'
       );

INSERT INTO reviews (id, book_id, content, rating, created_date)
VALUES (
           2000,
           2000,
           'A classic text that laid the groundwork for modern network theory.',
           4.8,
           '2024-12-01T14:45:00Z'
       );

-- BooksAuthors (Many-to-Many)
INSERT INTO books_authors (book_id, author_id) VALUES (1000, 1000);
INSERT INTO books_authors (book_id, author_id) VALUES (2000, 2000);
