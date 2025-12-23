package com.ld5ehom.graphql.domain.authors.repository;

import com.ld5ehom.graphql.domain.authors.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Provides persistence operations for Author entities used by graphql services.
//graphql 서비스에서 사용하는 Author 엔티티 영속성 접근 레이어
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}