package com.study.spring.bookstore.books.domain.mappers;

import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.domain.entities.BookEntity;

public interface BookMapper {
    BookEntity toBookEntity(BookCreateRequestDTO request);
}
