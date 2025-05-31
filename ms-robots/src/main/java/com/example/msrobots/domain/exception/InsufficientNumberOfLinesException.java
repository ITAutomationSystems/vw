package com.example.msrobots.domain.exception;

public class InsufficientNumberOfLinesException extends RuntimeException {
    public InsufficientNumberOfLinesException(String message) {
        super(message);
    }
}
