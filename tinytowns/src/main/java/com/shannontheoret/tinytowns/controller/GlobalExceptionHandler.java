package com.shannontheoret.tinytowns.controller;

import com.shannontheoret.tinytowns.exceptions.GameCodeNotFoundException;
import com.shannontheoret.tinytowns.exceptions.InternalGameException;
import com.shannontheoret.tinytowns.exceptions.InvalidMoveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidMoveException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMoveException(InvalidMoveException ex) {
        ErrorResponse errorResponse = new ErrorResponse("InvalidMove", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GameCodeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGameCodeNotFoundException(GameCodeNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("GameCodeNotFound", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InternalGameException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerException(InternalGameException ex) {
        ErrorResponse errorResponse = new ErrorResponse("InternalGameError", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Custom error response class
    static class ErrorResponse {
        private String errorCode;
        private String message;

        public ErrorResponse(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getMessage() {
            return message;
        }
    }
}
