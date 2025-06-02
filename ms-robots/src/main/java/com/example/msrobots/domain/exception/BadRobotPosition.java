package com.example.msrobots.domain.exception;

public class BadRobotPosition extends RuntimeException {
    public BadRobotPosition(String message) {
        super(message);
    }
}
