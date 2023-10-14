package com.study.spring.base.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusiness(BusinessException ex) {
        try {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ProblemType problemType = ProblemType.BUSINESS_ERROR;
            String detail = ex.getMessage();

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(detail)
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

}
