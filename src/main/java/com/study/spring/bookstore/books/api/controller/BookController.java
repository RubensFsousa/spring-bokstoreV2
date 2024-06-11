package com.study.spring.bookstore.books.api.controller;

import com.study.spring.base.shared.annotations.ApiController;
import com.study.spring.base.shared.annotations.OpenApiController;
import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.books.api.controller.annotation.CreateBookEndPoint;
import com.study.spring.bookstore.books.api.controller.annotation.GetBookByIdEndPoint;
import com.study.spring.bookstore.books.api.controller.annotation.GetBookPageEndPoint;
import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookDetailsResponseDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookPageResponseDTO;
import com.study.spring.bookstore.books.domain.services.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @GetBookPageEndPoint
    public PageResponse<GetBookPageResponseDTO> getAll(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "availableQuantity", required = false) Integer availableQuantity,
            @RequestParam(value = "launchDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate launchDate,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        var pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return bookService.getBookPage(search, availableQuantity, launchDate, pageable);
    }
}
