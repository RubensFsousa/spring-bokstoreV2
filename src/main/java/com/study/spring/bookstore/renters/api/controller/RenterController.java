package com.study.spring.bookstore.renters.api.controller;

import com.study.spring.base.shared.annotations.ApiController;
import com.study.spring.base.shared.annotations.OpenApiController;
import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.renters.api.controller.annotations.*;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.*;
import com.study.spring.bookstore.renters.domain.services.RenterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@OpenApiController(name = "Renters")
@ApiController(path = "/renter")
public class RenterController {

    private final RenterService renterService;

    @CreateRenterEndpoint
    public ResponseEntity<Void> create(@RequestBody @Valid RenterCreateRequestDTO request) {
        renterService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetRenterDetailsEndpoint
    public ResponseEntity<GetRenterDetailsResponseDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(renterService.getById(id), HttpStatus.OK);
    }

    @GetRenterPageEndpoint
    public ResponseEntity<PageResponse<GetRenterPageResponseDTO>> getPage(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        var pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return new ResponseEntity<>(renterService.getRenterPage(search, pageable), HttpStatus.OK);
    }

    @GetRenterRentsEndpoints
    public ResponseEntity<PageResponse<GetRenterRentsPageResponseDTO>> getRentsPage(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        var pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return new ResponseEntity<>(renterService.getRenterRentsPage(search, pageable), HttpStatus.OK);
    }

    @UpdateRenterEndpoint
    public ResponseEntity<Void> update(@RequestBody @Valid RenterUpdateRequestDTO request) {
        renterService.update(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteRenterEndpoint
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        renterService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
