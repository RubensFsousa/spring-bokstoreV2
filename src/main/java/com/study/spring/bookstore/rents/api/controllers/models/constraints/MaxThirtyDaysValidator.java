package com.study.spring.bookstore.rents.api.controllers.models.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class MaxThirtyDaysValidator implements ConstraintValidator<MaxThirtyDays, LocalDate> {

    @Override
    public void initialize(MaxThirtyDays constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true; // You may want to handle null values separately
        }
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusDays(30);
        return !value.isAfter(maxDate);
    }
}
