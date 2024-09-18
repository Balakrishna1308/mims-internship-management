package com.mfa.mims.exception;

public class ProgressNotFoundException extends RuntimeException{
    private String message;

    public ProgressNotFoundException(String message) {
        super(message);
    }
}
