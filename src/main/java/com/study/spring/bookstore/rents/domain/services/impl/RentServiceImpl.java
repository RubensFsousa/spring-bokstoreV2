package com.study.spring.bookstore.rents.domain.services.impl;

import com.study.spring.base.shared.exceptions.BusinessException;
import com.study.spring.base.shared.exceptions.EntityNotFoundException;
import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.books.domain.entities.BookEntity;
import com.study.spring.bookstore.books.domain.repositories.BookRepository;
import com.study.spring.bookstore.renters.domain.entities.RenterEntity;
import com.study.spring.bookstore.renters.domain.repositories.RenterRepository;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetMostRentedBookResponseDTO;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetRentPageResponseDTO;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.RentCreateRequestDTO;
import com.study.spring.bookstore.rents.domain.entities.RentEntity;
import com.study.spring.bookstore.rents.domain.enums.RentStatus;
import com.study.spring.bookstore.rents.domain.mappers.RentMapper;
import com.study.spring.bookstore.rents.domain.repositories.RentRepository;
import com.study.spring.bookstore.rents.domain.services.RentService;
import com.study.spring.bookstore.rents.domain.specs.RentSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final RentMapper rentMapper;
    private final BookRepository bookRepository;
    private final RenterRepository renterRepository;

    @Override
    public void create(RentCreateRequestDTO request) {
        var book = getBook(request.bookId());
        var renter = getRenter(request.renterId());

        validateRent(renter, book);
        validateDeadline(request.deadline());

        var rent = rentMapper.toRentEntity(request, book, renter);
        rentRepository.save(rent);
    }

    @Override
    public PageResponse<GetRentPageResponseDTO> getRentPage(String searchText, RentStatus status, PageRequest pageable) {
        updateRentStatuses();

        Specification<RentEntity> spec = Specification
                .where(RentSpecs.containsTextInAllColumns(searchText))
                .and(RentSpecs.rentStatusEquals(status));

        var rentsPage = rentRepository.findAll(spec, pageable);
        return rentMapper.toRentPageResponseDTO(rentsPage);
    }

    @Override
    public void update(Integer id) {
        updateRentStatuses();

        var rent = getRentByIdOrElseThrow(id);
        var today = LocalDate.now();
        var deadlineDate = rent.getDeadLineDate();

        if (rent.getStatus() == RentStatus.DELIVERED || rent.getStatus() == RentStatus.DELIVERED_WITH_DELAY) {
            throw new BusinessException("Rent already delivered");
        }

        if (today.isAfter(deadlineDate)) {
            rent.setStatus(RentStatus.DELIVERED_WITH_DELAY);
        } else {
            rent.setStatus(RentStatus.DELIVERED);
        }

        rent.setDevolutionDate(today);
        rentRepository.save(rent);
    }

    @Override
    public GetMostRentedBookResponseDTO mostRentedBook() {
        updateRentStatuses();

        var mostRented = rentRepository.findMostRentedBook();
        return rentMapper.toMostRentedBookResponseDTO(mostRented.get(0));
    }

    private void validateRent(RenterEntity renter, BookEntity book) {
        if (book.getIsDeleted() || book.getAvailableQuantity() == 0) {
            throw new BusinessException("This book is unavailable to rent");
        }
        if (renter.isDeleted()) {
            throw new BusinessException("This renter is unavailable to rent");
        }
        boolean hasPendingRent = renter.getRents().stream()
                .anyMatch(rent -> rent.getStatus() != RentStatus.DELIVERED && rent.getBook().equals(book));
        if (hasPendingRent) {
            throw new BusinessException("This renter has a pending rent with this book");
        }
    }

    private RentEntity getRentByIdOrElseThrow(Integer id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rent not found"));
    }

    private RenterEntity getRenter(Integer renterId) {
        return renterRepository.findById(renterId)
                .orElseThrow(() -> new EntityNotFoundException("Renter not found"));
    }

    private BookEntity getBook(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    private void updateRentStatuses() {
        var rents = rentRepository.findAll();
        var today = LocalDate.now();

        rents.forEach(rent -> {
            var currentStatus = rent.getStatus();
            var deadlineDate = rent.getDeadLineDate();

            if (deadlineDate.isAfter(today)) {
                if (currentStatus != RentStatus.DELIVERED && currentStatus != RentStatus.DELIVERED_WITH_DELAY) {
                    rent.setStatus(RentStatus.DELAYED);
                }
            } else if (deadlineDate.isBefore(today)) {
                if (currentStatus != RentStatus.DELIVERED) {
                    rent.setStatus(RentStatus.IN_TIME);
                }
            }
        });

        rentRepository.saveAll(rents);
    }

    private void validateDeadline(LocalDate deadLine) {
        LocalDate today = LocalDate.now();
        if (deadLine.isAfter(today.plusDays(30))) {
            throw new BusinessException("The deadline cannot be more than 30 days from the rent creation date.");
        }
    }
}
