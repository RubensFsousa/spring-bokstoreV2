package com.study.spring.bookstore.books.domain.services;

import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;

public interface BookService {
    void crete(BookCreateRequestDTO request);
}
