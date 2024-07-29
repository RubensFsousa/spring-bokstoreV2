package com.study.spring.base.authentication.api.controllers.users;

import com.study.spring.base.authentication.api.controllers.users.DTOs.CreateUserRequestDTO;
import com.study.spring.base.authentication.api.controllers.users.DTOs.GetUserDetailsResponseDTO;
import com.study.spring.base.authentication.api.controllers.users.DTOs.UpdateUserRequestDTO;
import com.study.spring.base.authentication.api.controllers.users.annotations.CreateUserEndpoint;
import com.study.spring.base.authentication.api.controllers.users.annotations.GetUserDetailsEndpoint;
import com.study.spring.base.authentication.api.controllers.users.annotations.UpdateUserEndpoint;
import com.study.spring.base.authentication.domain.services.UserService;
import com.study.spring.base.shared.annotations.ApiController;
import com.study.spring.base.shared.annotations.OpenApiController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetUserDetailsEndpoint
    public ResponseEntity<GetUserDetailsResponseDTO> getUserDetails(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
}
