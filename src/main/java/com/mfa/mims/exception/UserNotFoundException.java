package com.mfa.mims.exception;

public class UserNotFoundException  extends RuntimeException{
    private String message;

    public UserNotFoundException(String message) {
        super(message);
    }
}
