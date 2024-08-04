package com.study.spring.bookstore.rents.api.controllers;

import com.study.spring.base.shared.annotations.ApiController;
import com.study.spring.base.shared.annotations.OpenApiController;
import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.rents.api.controllers.annotations.GetMostRentedBook;
import com.study.spring.bookstore.rents.api.controllers.annotations.GetRenterRentsEndpoint;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetMostRentedBookResponseDTO;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetRenterRentsPageResponseDTO;
import com.study.spring.bookstore.rents.domain.services.RentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@AllArgsConstructor
@OpenApiController(name = "Rents")
@ApiController(path = "/rent")
public class RentDashboardController {

    private final RentService rentService;

    @GetMostRentedBook
    public ResponseEntity<GetMostRentedBookResponseDTO> mostRentedBook(@PathVariable Integer positions){
        return new ResponseEntity<>(rentService.mostRentedBook(positions), HttpStatus.OK);
    }

    @GetRenterRentsEndpoint
    public ResponseEntity<PageResponse<GetRenterRentsPageResponseDTO>> getRenterRents(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        var pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return new ResponseEntity<>(rentService.getRenterRentsPage(search, pageable), HttpStatus.OK);
    }
}
