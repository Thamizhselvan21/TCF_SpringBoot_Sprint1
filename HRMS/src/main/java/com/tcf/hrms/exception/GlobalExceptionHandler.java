// This program handles all application exceptions globally
// and returns standardized error responses to REST API clients.
package com.tcf.hrms.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles EmployeeNotFoundException and returns HTTP 404.
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EmployeeNotFoundException ex,
            HttpServletRequest request) 
    {

        return buildResponse(HttpStatus.NOT_FOUND,ex.getMessage(),request);
    }

    // Handles DuplicateEmailException and returns HTTP 409.
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(
            DuplicateEmailException ex,
            HttpServletRequest request) 
    {

        return buildResponse(HttpStatus.CONFLICT,ex.getMessage(),request);
    }

    // Handles validation errors and returns HTTP 400.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) 
    {

        // Collects all field validation errors into one message.
        String message = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField()
                    + ": " + error.getDefaultMessage())
            .collect(Collectors.joining("; "));

        return buildResponse(HttpStatus.BAD_REQUEST,message,request);
    }

    // Handles database constraint violations and returns HTTP 409.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDatabase(
            DataIntegrityViolationException ex,
            HttpServletRequest request) 
    {
        return buildResponse(HttpStatus.CONFLICT,"Database constraint violation",request);
    }

    // Handles unexpected exceptions and returns HTTP 500.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(
            Exception ex,
            HttpServletRequest request) 
    {
        return buildResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,"An unexpected error occurred",request);
    }

    // Creates a common error response with HTTP status,
    // error message, timestamp, and requested API path.
    private ResponseEntity<ErrorResponse> buildResponse(
            HttpStatus status,
            String message,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(),
            message,
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }
}  
