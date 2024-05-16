package com.example.bechestday.exception;

public class NotFoundException extends Exception {
    public NotFoundException() {
        super("The requested resource was not found.");
    }

    public NotFoundException(String message) {
        super(message);
    }
}