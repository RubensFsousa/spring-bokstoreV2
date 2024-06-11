package com.study.spring.bookstore.books.domain.repositories;

import com.study.spring.bookstore.books.domain.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer>, JpaSpecificationExecutor<BookEntity> {
}
