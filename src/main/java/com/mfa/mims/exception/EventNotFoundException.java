package com.mfa.mims.exception;

public class EventNotFoundException extends RuntimeException{
    private String message;

    public EventNotFoundException(String message)
    {
        super(message);
    }

}
