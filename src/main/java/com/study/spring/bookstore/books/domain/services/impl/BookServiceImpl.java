package com.study.spring.bookstore.books.domain.services.impl;

import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookDetailsResponseDTO;
import com.study.spring.bookstore.books.domain.entities.BookEntity;
import com.study.spring.bookstore.books.domain.mappers.BookMapper;
import com.study.spring.bookstore.books.domain.repositories.BookRepository;
import com.study.spring.bookstore.books.domain.services.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    private BookEntity getUserByIdOrElseThrow(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not Found"));
    }

}
