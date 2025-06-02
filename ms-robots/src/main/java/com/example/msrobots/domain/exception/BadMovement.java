package com.example.msrobots.domain.exception;

public class BadMovement extends RuntimeException {
    public BadMovement(String message) {
        super(message);
    }
}
