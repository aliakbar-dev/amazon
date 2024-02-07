package com.example.amazon.advice;

import com.example.amazon.exception.CustomerNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return ResponseEntity.badRequest().contentType(APPLICATION_JSON).body(exception.getMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationExceptions(ConstraintViolationException exception) {
        return ResponseEntity.badRequest().contentType(APPLICATION_JSON).body(exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(",")));
    }

}

