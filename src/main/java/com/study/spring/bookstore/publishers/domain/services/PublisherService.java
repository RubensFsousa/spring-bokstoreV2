package com.study.spring.bookstore.publishers.domain.services;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.GetPublisherDetailsResponseDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.GetPublisherPageResponseDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.PublisherCreateRequestDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.PublisherUpdateRequestDTO;
import org.springframework.data.domain.PageRequest;

public interface PublisherService {
    void create(PublisherCreateRequestDTO request);

    GetPublisherDetailsResponseDTO getById(Integer id);

    PageResponse<GetPublisherPageResponseDTO> getPublisherPage(String search, PageRequest pageable);

    void update(PublisherUpdateRequestDTO request);

    void delete(Integer id);
}
