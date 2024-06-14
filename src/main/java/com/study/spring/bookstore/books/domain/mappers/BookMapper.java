package com.study.spring.bookstore.books.domain.mappers;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookDetailsResponseDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookPageResponseDTO;
import com.study.spring.bookstore.books.domain.entities.BookEntity;
import org.springframework.data.domain.Page;

public interface BookMapper {
    BookEntity toBookEntity(BookCreateRequestDTO request);

    GetBookDetailsResponseDTO toBookDetailsResponseDTO(BookEntity book, Integer availableQuantity);

    PageResponse<GetBookPageResponseDTO> toBookPageResponseDTO(Page<BookEntity> booksPage);
}
