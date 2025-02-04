package com.trendyol.EstiMate.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

// REST API Errors
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Exception handler for InvalidVoteException
    @ExceptionHandler(InvalidVoteException.class)
    public ResponseEntity<Object> handleInvalidVoteException(InvalidVoteException ex) {
        // Generate a unique key (e.g., UUID)
        String errorKey = UUID.randomUUID().toString();

        // Create an ErrorResponse with the 3 parameters
        ErrorResponse errorResponse = new ErrorResponse("400", ex.getMessage(), errorKey);

        // Return the error response with HTTP 400 (Bad Request)
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // General exception handler for all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        // Generate a unique key (e.g., UUID)
        String errorKey = UUID.randomUUID().toString();

        // Create an ErrorResponse with the 3 parameters
        ErrorResponse errorResponse = new ErrorResponse("500", "Internal server error", errorKey);

        // Return the error response with HTTP 500 (Internal Server Error)
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
