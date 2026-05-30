package com.example.demo.validators.annotations;

import com.example.demo.validators.DniValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DniValidator.class) // Apuesta por el validador de abajo
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDni {
    String message() default "El DNI no es válido o la letra es incorrecta";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
