package com.study.spring.bookstore.books.domain.mappers.impl;

import com.study.spring.base.shared.exceptions.EntityNotFoundException;
import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookDetailsResponseDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookPageResponseDTO;
import com.study.spring.bookstore.books.domain.entities.BookEntity;
import com.study.spring.bookstore.books.domain.mappers.BookMapper;
import com.study.spring.bookstore.publishers.domain.entities.PublisherEntity;
import com.study.spring.bookstore.publishers.domain.repositories.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookMapperImpl implements BookMapper {

    private final PublisherRepository publisherRepository;

    @Override
    public BookEntity toBookEntity(BookCreateRequestDTO request, PublisherEntity publisherEntity) {
        return BookEntity.builder()
                .name(request.name())
                .author(request.author())
                .author(request.author())
                .totalQuantity(request.totalQuantity())
                .launchDate(request.launchDate())
                .publisher(publisherEntity)
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
                .publisherName(book.getPublisher().getName())
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
