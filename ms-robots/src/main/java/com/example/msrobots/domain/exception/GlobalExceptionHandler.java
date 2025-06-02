package com.example.msrobots.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientNumberOfLinesException.class)
    public ResponseEntity<String> handleRobotValidationException(InsufficientNumberOfLinesException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(OutOfBoundsException.class)
    public ResponseEntity<String> handleRobotValidationException(OutOfBoundsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(BadOrientation.class)
    public ResponseEntity<String> handleRobotValidationException(BadOrientation ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(BadRobotPosition.class)
    public ResponseEntity<String> handleRobotValidationException(BadRobotPosition ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(BadMovement.class)
    public ResponseEntity<String> handleRobotValidationException(BadMovement ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}