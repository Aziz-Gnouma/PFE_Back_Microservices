package com.example.congesabs.entity;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CumulativeCongesValidator.class)
public @interface CumulativeConges {
    String message() default "Invalid cumulative conges";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String otherFieldName();
}

