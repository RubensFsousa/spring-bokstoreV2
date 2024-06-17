package com.study.spring.bookstore.books.domain.services.impl;

import com.study.spring.base.shared.exceptions.BusinessException;
import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.books.api.controller.models.DTOs.BookCreateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.BookUpdateRequestDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookDetailsResponseDTO;
import com.study.spring.bookstore.books.api.controller.models.DTOs.GetBookPageResponseDTO;
import com.study.spring.bookstore.books.domain.entities.BookEntity;
import com.study.spring.bookstore.books.domain.mappers.BookMapper;
import com.study.spring.bookstore.books.domain.repositories.BookRepository;
import com.study.spring.bookstore.books.domain.services.BookService;
import com.study.spring.bookstore.books.domain.specs.BookSpecs;
import com.study.spring.bookstore.publishers.domain.entities.PublisherEntity;
import com.study.spring.bookstore.publishers.domain.repositories.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public void crete(BookCreateRequestDTO request) {
        var publisher = getPublisherByIdOrElseThrow(request.publisherId());
        var book = bookMapper.toBookEntity(request, publisher);
        validateBookName(book);

        bookRepository.save(book);
    }

    @Override
    public GetBookDetailsResponseDTO getById(Integer id) {
        var book = getBookByIdOrElseThrow(id);
        //TODO add rent logic
//        int availableQuantity = book.getTotalQuantity() - rentsInProcess
        return bookMapper.toBookDetailsResponseDTO(book, 1);
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

    @Override
    public void update(BookUpdateRequestDTO request) {
        var book = getBookByIdOrElseThrow(request.id());

        book = book.toBuilder()
                .name(request.name())
                .author(request.author())
                .totalQuantity(request.totalQuantity())
                .launchDate(request.launchDate())
                .publisher(getPublisherByIdOrElseThrow(request.publisherId()))
                .build();

        validateBookName(book);
        validateBookQuantity(book);

        bookRepository.save(book);
    }

    @Override
    public void delete(Integer id) {
        //TODO: insert rent logic
        bookRepository.delete(getBookByIdOrElseThrow(id));
    }

    private PublisherEntity getPublisherByIdOrElseThrow(Integer id) {
        return publisherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Publisher not Found"));
    }

    private void validateBookQuantity(BookEntity book) {
        var savedBook = bookRepository.findById(book.getId()).orElse(null);
        if (savedBook != null && book.getTotalQuantity() < savedBook.getTotalQuantity()) {
            throw new BusinessException("BookTotalQuantityCannotBeDecreased");
        }
    }

    private void validateBookName(BookEntity book) {
        var savedBook = bookRepository.findByName(book.getName()).orElse(null);
        if (savedBook != null && !savedBook.getId().equals(book.getId())) {
            throw new BusinessException("BookNameAlreadyExists");
        }
    }

    private BookEntity getBookByIdOrElseThrow(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not Found"));
    }

}
