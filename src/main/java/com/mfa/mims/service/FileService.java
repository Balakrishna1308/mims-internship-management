package com.mfa.mims.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

public interface FileService {
    CompletableFuture<Boolean> uploadFile(MultipartFile file);
}
