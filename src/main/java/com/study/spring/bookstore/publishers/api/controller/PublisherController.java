package com.study.spring.bookstore.publishers.api.controller;

import com.study.spring.base.shared.annotations.ApiController;
import com.study.spring.base.shared.annotations.OpenApiController;
import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.publishers.api.controller.annotations.*;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.GetPublisherDetailsResponseDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.GetPublisherPageResponseDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.PublisherCreateRequestDTO;
import com.study.spring.bookstore.publishers.api.controller.models.DTOs.PublisherUpdateRequestDTO;
import com.study.spring.bookstore.publishers.domain.services.PublisherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@OpenApiController(name = "Publishers")
@ApiController(path = "/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    @CreatePublisherEndpoint
    public ResponseEntity<Void> create(@RequestBody @Valid PublisherCreateRequestDTO request) {
        publisherService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetPublisherDetailsEndpoint
    public ResponseEntity<GetPublisherDetailsResponseDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(publisherService.getById(id), HttpStatus.OK);
    }

    @GetPublisherPageEndpoint
    public ResponseEntity<PageResponse<GetPublisherPageResponseDTO>> getPage(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        var pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return new ResponseEntity<>(publisherService.getPublisherPage(search, pageable), HttpStatus.OK);
    }

    @UpdatePublisherEndpoint
    public ResponseEntity<Void> update(@RequestBody @Valid PublisherUpdateRequestDTO request) {
        publisherService.update(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeletePublisherEndpoint
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        publisherService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
