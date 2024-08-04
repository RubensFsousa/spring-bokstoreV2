package com.study.spring.bookstore.rents.domain.mappers.impl;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.books.domain.entities.BookEntity;
import com.study.spring.bookstore.renters.domain.entities.RenterEntity;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetMostRentedBookResponseDTO;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetRentPageResponseDTO;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.RentCreateRequestDTO;
import com.study.spring.bookstore.rents.domain.entities.RentEntity;
import com.study.spring.bookstore.rents.domain.mappers.RentMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import static com.study.spring.bookstore.rents.domain.enums.RentStatus.IN_TIME;

@Component
public class RentMapperImpl implements RentMapper {

    @Override
    public RentEntity toRentEntity(RentCreateRequestDTO request, BookEntity book, RenterEntity renter) {
        return RentEntity.builder()
                .renter(renter)
                .book(book)
                .deadLineDate(request.deadline())
                .status(IN_TIME)
                .build();
    }

    @Override
    public PageResponse<GetRentPageResponseDTO> toRentPageResponseDTO(Page<RentEntity> rentsPage) {
        var content = rentsPage.getContent().stream().map(rent -> GetRentPageResponseDTO.builder()
                .id(rent.getId())
                .renterName(rent.getRenter().getName())
                .bookName(rent.getBook().getName())
                .status(rent.getStatus())
                .devolutionDate(rent.getDevolutionDate())
                .deadLineDate(rent.getDeadLineDate())
                .rentDate(rent.getCreatedAt().toLocalDate())
                .build()).toList();

        return new PageResponse<>(
                content,
                rentsPage.getNumber(),
                rentsPage.getSize(),
                rentsPage.getTotalElements(),
                rentsPage.getTotalPages()
        );
    }

    @Override
    public GetMostRentedBookResponseDTO toMostRentedBookResponseDTO(BookEntity mostRentedBook) {
        return GetMostRentedBookResponseDTO.builder()
                .bookName(mostRentedBook.getName())
                .rentedNumber(mostRentedBook.getRents().size())
                .build();
    }

}
