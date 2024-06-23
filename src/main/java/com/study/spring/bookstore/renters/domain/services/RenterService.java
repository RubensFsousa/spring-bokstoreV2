package com.study.spring.bookstore.renters.domain.services;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.GetRenterDetailsResponseDTO;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.GetRenterPageResponseDTO;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.RenterCreateRequestDTO;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.RenterUpdateRequestDTO;
import org.springframework.data.domain.PageRequest;

public interface RenterService {
    void create(RenterCreateRequestDTO request);

    GetRenterDetailsResponseDTO getById(Integer id);

    PageResponse<GetRenterPageResponseDTO> getRenterPage(String search, PageRequest pageable);

    void update(RenterUpdateRequestDTO request);

    void delete(Integer id);
}
