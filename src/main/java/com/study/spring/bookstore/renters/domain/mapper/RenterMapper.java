package com.study.spring.bookstore.renters.domain.mapper;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.GetRenterDetailsResponseDTO;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.GetRenterPageResponseDTO;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.RenterCreateRequestDTO;
import com.study.spring.bookstore.renters.domain.entities.RenterEntity;
import org.springframework.data.domain.Page;

public interface RenterMapper {
    RenterEntity toRenterEntity(RenterCreateRequestDTO request);

    GetRenterDetailsResponseDTO toRenterDetailsResponseDTO(RenterEntity renter);

    PageResponse<GetRenterPageResponseDTO> toRenterPageResponseDTO(Page<RenterEntity> rentersPage);
}
