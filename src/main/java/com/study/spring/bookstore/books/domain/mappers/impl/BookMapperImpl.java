package com.study.spring.bookstore.books.domain.mappers.impl;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookDetailsResponseDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookPageResponseDTO;
import com.study.spring.bookstore.books.domain.entities.BookEntity;
import com.study.spring.bookstore.books.domain.mappers.BookMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookEntity toBookEntity(BookCreateRequestDTO request) {
        return BookEntity.builder()
                .name(request.name())
                .author(request.author())
                .author(request.author())
                .totalQuantity(request.totalQuantity())
                .launchDate(request.launchDate())
                .build();
    }

    @Override
    public GetBookDetailsResponseDTO toBookDetailsResponseDTO(BookEntity book, Integer availableQuantity) {
        return GetBookDetailsResponseDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .totalQuantity(book.getTotalQuantity())
                .availableQuantity(availableQuantity)
                .launchDate(book.getLaunchDate())
                .build();
    }

    @Override
    public PageResponse<GetBookPageResponseDTO> toBookPageResponseDTO(Page<BookEntity> booksPage) {
        var content = booksPage.getContent().stream().map(book -> GetBookPageResponseDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .totalQuantity(book.getTotalQuantity())
                .build()).toList();

        return new PageResponse<>(
                content,
                booksPage.getNumber(),
                booksPage.getSize(),
                booksPage.getTotalElements(),
                booksPage.getTotalPages()
        );
    }
}
