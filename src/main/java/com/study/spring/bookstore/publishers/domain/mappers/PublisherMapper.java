package com.study.spring.bookstore.publishers.domain.mappers;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.GetPublisherDetailsResponseDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.GetPublisherPageResponseDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.PublisherCreateRequestDTO;
import com.study.spring.bookstore.publishers.domain.entities.PublisherEntity;
import org.springframework.data.domain.Page;

public interface PublisherMapper {
    PublisherEntity toPublisherEntity(PublisherCreateRequestDTO request);

    GetPublisherDetailsResponseDTO toPublisherDetailsResponseDTO(PublisherEntity publisher);

    PageResponse<GetPublisherPageResponseDTO> toPublisherPageResponseDTO(Page<PublisherEntity> publishersPage);
}
