package com.study.spring.bookstore.publishers.domain.mappers.impl;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.GetPublisherDetailsResponseDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.GetPublisherPageResponseDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.PublisherCreateRequestDTO;
import com.study.spring.bookstore.publishers.domain.entities.PublisherEntity;
import com.study.spring.bookstore.publishers.domain.mappers.PublisherMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapperImpl implements PublisherMapper {

    @Override
    public PublisherEntity toPublisherEntity(PublisherCreateRequestDTO request) {
        return PublisherEntity.builder()
                .name(request.name())
                .email(request.email())
                .telephone(request.telephone())
                .site(request.site())
                .build();
    }

    @Override
    public GetPublisherDetailsResponseDTO toPublisherDetailsResponseDTO(PublisherEntity publisher) {
        return GetPublisherDetailsResponseDTO.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .email(publisher.getEmail())
                .telephone(publisher.getTelephone())
                .site(publisher.getSite())
                .build();
    }

    @Override
    public PageResponse<GetPublisherPageResponseDTO> toPublisherPageResponseDTO(Page<PublisherEntity> publishersPage) {
        var content = publishersPage.getContent().stream().map(publisher -> GetPublisherPageResponseDTO.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .build()).toList();

        return new PageResponse<>(
                content,
                publishersPage.getNumber(),
                publishersPage.getSize(),
                publishersPage.getTotalElements(),
                publishersPage.getTotalPages()
        );
    }

}
