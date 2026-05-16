package com.example.demo.exception.handler;

import com.example.demo.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class PersonNotFoundExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<?> handleRuntime(PersonNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "error", "Person not found",
                        "message", ex.getMessage()
                ));
    }
}