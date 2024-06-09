package com.study.spring.bookstore.books.domain.services.impl;

import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.domain.mappers.BookMapper;
import com.study.spring.bookstore.books.domain.repositories.BookRepository;
import com.study.spring.bookstore.books.domain.services.BookService;
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


}
