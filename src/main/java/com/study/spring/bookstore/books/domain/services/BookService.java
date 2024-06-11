package com.study.spring.bookstore.books.domain.services;

import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookDetailsResponseDTO;

public interface BookService {
    void crete(BookCreateRequestDTO request);

    GetBookDetailsResponseDTO getById(Integer id);
}
