package com.study.spring.base.authentication.api.controllers.user.v1;

import com.study.spring.base.authentication.api.controllers.user.v1.models.DTOs.UserCreateRequest;
import com.study.spring.base.authentication.domain.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> getTest() {
        return new ResponseEntity<>("teste", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> postTest(@RequestBody @Valid UserCreateRequest request) {
        userService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
