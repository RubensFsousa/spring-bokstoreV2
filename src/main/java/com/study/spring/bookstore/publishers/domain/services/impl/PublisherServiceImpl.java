package com.study.spring.bookstore.publishers.domain.services.impl;

import com.study.spring.base.shared.exceptions.BusinessException;
import com.study.spring.base.shared.exceptions.EntityNotFoundException;
import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.GetPublisherDetailsResponseDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.GetPublisherPageResponseDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.PublisherCreateRequestDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.PublisherUpdateRequestDTO;
import com.study.spring.bookstore.publishers.domain.entities.PublisherEntity;
import com.study.spring.bookstore.publishers.domain.mappers.PublisherMapper;
import com.study.spring.bookstore.publishers.domain.repositories.PublisherRepository;
import com.study.spring.bookstore.publishers.domain.services.PublisherService;
import com.study.spring.bookstore.publishers.domain.specs.PublisherSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Override
    public void create(PublisherCreateRequestDTO request) {
        var publisher = publisherMapper.toPublisherEntity(request);
        validatePublisherName(publisher);
        
        publisherRepository.save(publisher);
    }

    @Override
    public GetPublisherDetailsResponseDTO getById(Integer id) {
        var publisher = getPublisherByIdOrElseThrow(id);
        return publisherMapper.toPublisherDetailsResponseDTO(publisher);
    }

    @Override
    public PageResponse<GetPublisherPageResponseDTO> getPublisherPage(String search, PageRequest pageable) {
        Specification<PublisherEntity> spec = Specification
                .where(PublisherSpecs.containsTextInAllColumns(search));

        var publishersPage = publisherRepository.findAll(spec, pageable);
        return publisherMapper.toPublisherPageResponseDTO(publishersPage);
    }

    @Override
    public void update(PublisherUpdateRequestDTO request) {
        var publisher = getPublisherByIdOrElseThrow(request.id());

        publisher = publisher.toBuilder()
                .name(request.name())
                .email(request.email())
                .telephone(request.telephone())
                .site(request.site())
                .build();

        validatePublisherName(publisher);

        publisherRepository.save(publisher);
    }

    @Override
    public void delete(Integer id) {
        var publisher = getPublisherByIdOrElseThrow(id);
        if (!publisher.getBooks().isEmpty()){
            throw new BusinessException("Already books using this publisher");
        }
        publisherRepository.delete(publisher);
    }

    private PublisherEntity getPublisherByIdOrElseThrow(Integer id) {
        return publisherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Publisher not Found"));
    }

    private void validatePublisherName(PublisherEntity publisher) {
        var savedPublisher = publisherRepository.findByName(publisher.getName()).orElse(null);
        if (savedPublisher != null && !savedPublisher.getId().equals(publisher.getId())) {
            throw new BusinessException("PublisherNameAlreadyExists");
        }
    }

}
