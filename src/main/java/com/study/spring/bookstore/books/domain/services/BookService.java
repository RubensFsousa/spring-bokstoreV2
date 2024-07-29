package com.study.spring.bookstore.books.domain.services;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.BookUpdateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookDetailsResponseDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookPageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface BookService {
    void create(BookCreateRequestDTO request);
    GetBookDetailsResponseDTO getById(Integer id);
    PageResponse<GetBookPageResponseDTO> getBookPage(String searchText, Integer availableQuantity, LocalDate launchDate, Pageable pageable);
    void update(BookUpdateRequestDTO request);
    void delete(Integer id);

}
