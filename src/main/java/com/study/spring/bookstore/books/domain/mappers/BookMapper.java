package com.study.spring.bookstore.books.domain.mappers;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookDetailsResponseDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookPageResponseDTO;
import com.study.spring.bookstore.books.domain.entities.BookEntity;
import com.study.spring.bookstore.publishers.domain.entities.PublisherEntity;
import org.springframework.data.domain.Page;

public interface BookMapper {
    BookEntity toBookEntity(BookCreateRequestDTO request, PublisherEntity publisherEntity);

    GetBookDetailsResponseDTO toBookDetailsResponseDTO(BookEntity book);

    PageResponse<GetBookPageResponseDTO> toBookPageResponseDTO(Page<BookEntity> booksPage);
}
