package com.study.spring.bookstore.rents.domain.mappers;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.books.domain.entities.BookEntity;
import com.study.spring.bookstore.renters.domain.entities.RenterEntity;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetMostRentedBookResponseDTO;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetRentPageResponseDTO;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.RentCreateRequestDTO;
import com.study.spring.bookstore.rents.domain.entities.RentEntity;
import org.springframework.data.domain.Page;

public interface RentMapper {
    RentEntity toRentEntity(RentCreateRequestDTO request, BookEntity book, RenterEntity renter);

    PageResponse<GetRentPageResponseDTO> toRentPageResponseDTO(Page<RentEntity> rentsPage);

    GetMostRentedBookResponseDTO toMostRentedBookResponseDTO(BookEntity mostRentedBook);
}
