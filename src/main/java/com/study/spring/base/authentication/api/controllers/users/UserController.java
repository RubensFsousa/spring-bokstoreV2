package com.study.spring.base.authentication.api.controllers.users;

import com.study.spring.base.authentication.api.controllers.users.DTOs.CreateUserRequestDTO;
import com.study.spring.base.authentication.api.controllers.users.annotations.CreateUserEndPoint;
import com.study.spring.base.authentication.domain.services.UserService;
import com.study.spring.base.shared.annotations.ApiController;
import com.study.spring.base.shared.annotations.OpenApiController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@OpenApiController(name = "User")
@ApiController(path = "/users")
public class UserController {

    private final UserService userService;

    @CreateUserEndPoint
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequestDTO request) {
        userService.createUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
