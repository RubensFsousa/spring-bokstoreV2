package com.study.spring.bookstore.rents.api.controllers;

import com.study.spring.base.shared.annotations.ApiController;
import com.study.spring.base.shared.annotations.OpenApiController;
import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.rents.api.controllers.annotations.CreateRentEndpoint;
import com.study.spring.bookstore.rents.api.controllers.annotations.DeliveryRentEndpoint;
import com.study.spring.bookstore.rents.api.controllers.annotations.GetRentPageEndpoint;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetRentPageResponseDTO;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.RentCreateRequestDTO;
import com.study.spring.bookstore.rents.domain.enums.RentStatus;
import com.study.spring.bookstore.rents.domain.services.RentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@AllArgsConstructor
@OpenApiController(name = "Rents")
@ApiController(path = "/rent")
public class RentController {

    private final RentService rentService;

    @CreateRentEndpoint
    public ResponseEntity<Void> create(@RequestBody @Valid RentCreateRequestDTO request) {
        rentService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetRentPageEndpoint
    public ResponseEntity<PageResponse<GetRentPageResponseDTO>> getPage(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "status", required = false) RentStatus status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        var pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return new ResponseEntity<>(rentService.getRentPage(search, status, pageable), HttpStatus.OK);
    }

    @DeliveryRentEndpoint
    public ResponseEntity<Void> update(@PathVariable Integer id) {
        rentService.update(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
