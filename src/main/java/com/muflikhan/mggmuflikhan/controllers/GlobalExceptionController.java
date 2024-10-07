package com.muflikhan.mggmuflikhan.controllers;

import com.muflikhan.mggmuflikhan.dtos.ApiResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionController  {


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("msg", ex.getMessage());

        ApiResponse<Object> response = new ApiResponse<>(
                "error",
                null,
                ex.getReason(),
                errorDetails
        );

        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();

        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
           errors.put(violation.getPropertyPath().toString(),violation.getMessage());
        }
            ApiResponse<Object> response = new ApiResponse<>(
                "error",
                null,
                "Validation failed",
                errors
        );



        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
