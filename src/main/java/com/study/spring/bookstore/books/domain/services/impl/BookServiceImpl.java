package com.study.spring.bookstore.books.domain.services.impl;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookDetailsResponseDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookPageResponseDTO;
import com.study.spring.bookstore.books.domain.entities.BookEntity;
import com.study.spring.bookstore.books.domain.mappers.BookMapper;
import com.study.spring.bookstore.books.domain.repositories.BookRepository;
import com.study.spring.bookstore.books.domain.services.BookService;
import com.study.spring.bookstore.books.domain.specs.BookSpecs;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public void crete(BookCreateRequestDTO request) {
        var book = bookMapper.toBookEntity(request);
        bookRepository.save(book);
    }

    @Override
    public GetBookDetailsResponseDTO getById(Integer id) {
        var book = getUserByIdOrElseThrow(id);
        return bookMapper.toBookDetailsResponseDTO(book);
    }

    @Override
    public PageResponse<GetBookPageResponseDTO> getBookPage(String searchText, Integer availableQuantity, LocalDate launchDate, Pageable pageable) {
        Specification<BookEntity> spec = Specification
                .where(BookSpecs.containsTextInAllColumns(searchText))
                .and(BookSpecs.availableQuantityEquals(availableQuantity))
                .and(BookSpecs.launchDateEquals(launchDate));

        var booksPage = bookRepository.findAll(spec, pageable);
        return bookMapper.toBookPageResponseDTO(booksPage);
    }

    private BookEntity getUserByIdOrElseThrow(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not Found"));
    }

}
