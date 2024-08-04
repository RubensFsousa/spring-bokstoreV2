package com.study.spring.bookstore.rents.domain.services;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetMostRentedBookResponseDTO;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetRentPageResponseDTO;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetRenterRentsPageResponseDTO;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.RentCreateRequestDTO;
import com.study.spring.bookstore.rents.domain.enums.RentStatus;
import org.springframework.data.domain.PageRequest;

public interface RentService {
    void create(RentCreateRequestDTO request);

    PageResponse<GetRentPageResponseDTO> getRentPage(String search, RentStatus status, PageRequest pageable);

    void update(Integer id);

    GetMostRentedBookResponseDTO mostRentedBook(Integer positions);

    PageResponse<GetRenterRentsPageResponseDTO> getRenterRentsPage(String search, PageRequest pageable);
}
