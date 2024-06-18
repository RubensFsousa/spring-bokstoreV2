package com.study.spring.bookstore.renters.domain.mapper.impl;

import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.GetRenterDetailsResponseDTO;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.GetRenterPageResponseDTO;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.RenterCreateRequestDTO;
import com.study.spring.bookstore.renters.domain.entities.RenterEntity;
import com.study.spring.bookstore.renters.domain.mapper.RenterMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class RenterMapperImpl implements RenterMapper {

    @Override
    public RenterEntity toRenterEntity(RenterCreateRequestDTO request) {
        return RenterEntity.builder()
                .name(request.name())
                .email(request.email())
                .telephone(request.telephone())
                .address(request.address())
                .cpf(request.cpf())
                .build();
    }

    @Override
    public GetRenterDetailsResponseDTO toRenterDetailsResponseDTO(RenterEntity renter) {
        return GetRenterDetailsResponseDTO.builder()
                .id(renter.getId())
                .name(renter.getName())
                .email(renter.getEmail())
                .telephone(renter.getTelephone())
                .address(renter.getAddress())
                .cpf(renter.getCpf())
                .build();
    }

    @Override
    public PageResponse<GetRenterPageResponseDTO> toRenterPageResponseDTO(Page<RenterEntity> rentersPage) {
        var content = rentersPage.getContent().stream().map(renter -> GetRenterPageResponseDTO.builder()
                .id(renter.getId())
                .name(renter.getName())
                .email(renter.getEmail())
                .build()).toList();

        return new PageResponse<>(
                content,
                rentersPage.getNumber(),
                rentersPage.getSize(),
                rentersPage.getTotalElements(),
                rentersPage.getTotalPages()
        );
    }
}
