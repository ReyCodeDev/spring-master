package com.example.demo.validators;

import com.example.demo.validators.annotations.ValidDni;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class DniValidator implements ConstraintValidator<ValidDni, String> {
    @Override
    public void initialize(ValidDni constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    private static final Pattern DNI_PATTERN = Pattern.compile("^[0-9]{8}[A-Za-z]$");
    private static final String LETTERS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isBlank()) return false;
        String dni = s.trim().toUpperCase();
        if (!DNI_PATTERN.matcher(dni).matches()) return false;
        try {
            return dni.charAt(8) == LETTERS_DNI.charAt(Integer.parseInt(dni.substring(0, 8)) % 23);
        } catch (Exception e) {
            return false;
        }
    }
}
