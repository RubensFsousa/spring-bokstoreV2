package com.study.spring.bookstore.books.api.controller.annotation;


import com.study.spring.base.authentication.domain.models.enums.Roles;
import com.study.spring.base.shared.annotations.OpenApiResponse201;
import com.study.spring.base.shared.annotations.OpenApiResponse400;
import com.study.spring.base.shared.annotations.OpenApiResponse401;
import com.study.spring.base.shared.annotations.OpenApiResponse403;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Operation(
        summary = "Create Book",
        description = "Requires role: " + Roles.Name.ADMIN
)
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(method = RequestMethod.POST, produces = "application/json")
@OpenApiResponse201
@OpenApiResponse400
@OpenApiResponse401
@OpenApiResponse403
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateBookEndPoint {
}
