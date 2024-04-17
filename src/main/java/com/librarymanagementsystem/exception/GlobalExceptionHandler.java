package com.librarymanagementsystem.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.librarymanagementsystem.model.ErrorResponse;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(AuthenticationException e , WebRequest request) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .apiPath(request.getDescription(false))
                        .httpStatus(HttpStatus.UNAUTHORIZED)
                        .errorMsg(e.getMessage())
                        .errorTime(LocalDateTime.now())
                        .build()
                ,
                HttpStatus.UNAUTHORIZED
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex , WebRequest request) throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .apiPath(request.getDescription(false))
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .errorMsg(errors.toString())
                        .errorTime(LocalDateTime.now())
                        .build()
                ,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(RuntimeException e , WebRequest request) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .apiPath(request.getDescription(false))
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .errorMsg(e.getMessage())
                        .errorTime(LocalDateTime.now())
                        .build()
                ,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException e , WebRequest request) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .apiPath(request.getDescription(false))
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .errorMsg(e.getMessage())
                        .errorTime(LocalDateTime.now())
                        .build()
                ,
                HttpStatus.BAD_REQUEST
        );
    }
}
