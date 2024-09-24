package com.mfa.mims.service;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public interface FileService {
    CompletableFuture<Boolean> uploadFile(File file);
}
