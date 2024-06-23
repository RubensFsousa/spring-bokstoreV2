package com.study.spring.bookstore.rents.api.controllers.models.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MaxThirtyDaysValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxThirtyDays {
    String message() default "The date must be within 30 days from today";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
