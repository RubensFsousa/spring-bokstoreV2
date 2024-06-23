package com.study.spring.bookstore.rents.api.controllers;

import com.study.spring.base.shared.annotations.ApiController;
import com.study.spring.base.shared.annotations.OpenApiController;
import com.study.spring.bookstore.rents.api.controllers.annotations.GetMostRentedBook;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetMostRentedBookResponseDTO;
import com.study.spring.bookstore.rents.domain.services.RentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@OpenApiController(name = "Rents")
@ApiController(path = "/rent")
public class RentDashboardController {

    private final RentService rentService;

    @GetMostRentedBook
    public ResponseEntity<GetMostRentedBookResponseDTO> mostRentedBook(){
        return new ResponseEntity<>(rentService.mostRentedBook(), HttpStatus.OK);
    }
}
