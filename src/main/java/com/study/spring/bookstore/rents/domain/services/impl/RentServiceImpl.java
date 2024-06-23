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

import java.time.LocalDate;
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
        var rent = rentMapper.toRentEntity(request, book, renter);

        rentRepository.save(rent);
    }

    @Override
    public PageResponse<GetRentPageResponseDTO> getRentPage(String searchText, RentStatus status, PageRequest pageable) {
        Specification<RentEntity> spec = Specification
                .where(RentSpecs.containsTextInAllColumns(searchText))
                .and(RentSpecs.rentStatusEquals(status));

        var rentsPage = rentRepository.findAll(spec, pageable);
        return rentMapper.toRentPageResponseDTO(rentsPage);
    }

    @Override
    public void update(Integer id) {
        var rent = getRentByIdOrElseThrow(id);
        if (rent.getStatus().equals(RentStatus.DELIVERED)){
            throw new BusinessException("Rent already delivered");
        }
        rent.toBuilder()
                .status(RentStatus.DELIVERED)
                .devolutionDate(LocalDate.now())
                .build();
    }

    @Override
    public GetMostRentedBookResponseDTO mostRentedBook() {
        var mostRented = rentRepository.findMostRentedBook().stream().findFirst().orElse(null);
        return rentMapper.toMostRentedBookResponseDTO(mostRented);
    }

    private void validateRent(RenterEntity renter, BookEntity book) {
        if (book.getIsDeleted() || book.getAvailableQuantity().equals(0))
            throw new BusinessException("this book is unavailable to rent");
        if (renter.isDeleted()) throw new BusinessException("this renter is unavailable to rent");
        if (renter.getRents().stream()
                .filter(rent -> !rent.getStatus().equals(RentStatus.DELIVERED))
                .anyMatch(rent -> rent.getBook().equals(book))
        ) {
            throw new BusinessException("this renter have a pendency with this book");
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

}
