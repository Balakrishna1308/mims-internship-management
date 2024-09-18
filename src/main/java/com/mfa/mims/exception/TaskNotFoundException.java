package com.mfa.mims.exception;

public class TaskNotFoundException extends RuntimeException{
    private String message;

    public TaskNotFoundException(String message) {
        super(message);
    }
}
