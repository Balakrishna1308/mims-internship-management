package com.mfa.mims.exception.file_exception;

import java.io.IOException;

public class FileStorageException extends RuntimeException{

    public FileStorageException(String message, IOException ioException)
    {
        super(message);
    }

    public FileStorageException(String fileSize) {

    }
}
