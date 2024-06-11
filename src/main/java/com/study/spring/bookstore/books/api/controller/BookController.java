package com.study.spring.bookstore.books.api.controller;

import com.study.spring.base.shared.annotations.ApiController;
import com.study.spring.base.shared.annotations.OpenApiController;
import com.study.spring.bookstore.books.api.controller.annotation.CreateBookEndPoint;
import com.study.spring.bookstore.books.api.controller.annotation.GetBookByIdEndPoint;
import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookDetailsResponseDTO;
import com.study.spring.bookstore.books.domain.services.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@OpenApiController(name = "Books")
@ApiController(path = "/book")
public class BookController {

    private final BookService bookService;

    @CreateBookEndPoint
    public ResponseEntity<Void> create(@RequestBody @Valid BookCreateRequestDTO request) {
        bookService.crete(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetBookByIdEndPoint
    public ResponseEntity<GetBookDetailsResponseDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
    }
}
