# Database Schema

## 1) Books

| 타입     | 필드명           | 제약 | 설명    |
|--------|---------------|----|-------|
| int    | id            | PK | 서적 ID |
| string | title         |    | 서적 제목 |
| string | publisher     |    | 출판사   |
| date   | publishedDate |    | 출판일   |

## 2) Authors

| 타입     | 필드명  | 제약 | 설명    |
|--------|------|----|-------|
| int    | id   | PK | 저자 ID |
| string | name |    | 저자 이름 |

## 3) Reviews

| 타입     | 필드명         | 제약 | 설명    |
|--------|-------------|----|-------|
| int    | id          | PK | 리뷰 ID |
| int    | bookID      | FK | 서적 ID |
| string | content     |    | 리뷰 내용 |
| float  | rating      |    | 평점    |
| date   | createdDate |    | 작성일   |

## 4) BooksAuthors

| 타입  | 필드명      | 제약 | 설명    |
|-----|----------|----|-------|
| int | bookId   | FK | 서적 ID |
| int | authorId | FK | 저자 ID |

## 5) Relations

| 관계                | 설명     |
|-------------------|--------|
| Books 1:N Reviews | has    |
| Books N:M Authors | has    |
| Authors N:M Books | writes |
