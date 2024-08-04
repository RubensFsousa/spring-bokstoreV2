package com.study.spring.base.authentication.api.controllers.users;

import com.study.spring.base.authentication.api.controllers.users.DTOs.CreateUserRequestDTO;
import com.study.spring.base.authentication.api.controllers.users.DTOs.GetUserDetailsResponseDTO;
import com.study.spring.base.authentication.api.controllers.users.DTOs.GetUserPageResponseDTO;
import com.study.spring.base.authentication.api.controllers.users.DTOs.UpdateUserRequestDTO;
import com.study.spring.base.authentication.api.controllers.users.annotations.CreateUserEndpoint;
import com.study.spring.base.authentication.api.controllers.users.annotations.GetUserDetailsEndpoint;
import com.study.spring.base.authentication.api.controllers.users.annotations.GetUserPageEndpoint;
import com.study.spring.base.authentication.api.controllers.users.annotations.UpdateUserEndpoint;
import com.study.spring.base.authentication.domain.services.UserService;
import com.study.spring.base.shared.annotations.ApiController;
import com.study.spring.base.shared.annotations.OpenApiController;
import com.study.spring.base.shared.models.PageResponse;
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
@OpenApiController(name = "User")
@ApiController(path = "/users")
public class UserController {

    private final UserService userService;

    @CreateUserEndpoint
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequestDTO request) {
        userService.createUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @UpdateUserEndpoint
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UpdateUserRequestDTO request) {
        userService.updateUser(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetUserPageEndpoint
    public ResponseEntity<PageResponse<GetUserPageResponseDTO>> getUserPage(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        var pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return new ResponseEntity<>(userService.getUserPage(search, pageable), HttpStatus.OK);
    }

    @GetUserDetailsEndpoint
    public ResponseEntity<GetUserDetailsResponseDTO> getUserDetails(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
}
