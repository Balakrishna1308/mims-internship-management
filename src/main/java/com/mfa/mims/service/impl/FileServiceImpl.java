
package com.mfa.mims.service.impl;

import com.mfa.mims.entity.FileEntity;
import com.mfa.mims.exception.file_exception.FileStorageException;
import com.mfa.mims.repository.FileRepository;
import com.mfa.mims.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Boolean> uploadFile(MultipartFile file) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                    if (file.getSize() > 1_000_000) {
                    throw new FileStorageException("File size exceeds the limit...");
                }

                // Get file data
                byte[] fileData = file.getBytes();

                // Create file entity
                FileEntity fileEntity = new FileEntity(
                        file.getOriginalFilename(),
                        file.getContentType(),
                        fileData
                );

                // Save file entity in the database
                fileRepository.save(fileEntity);

                System.out.println("File uploaded and saved to database successfully: " + file.getOriginalFilename());
                return true;
            } catch (IOException ioException) {
                throw new FileStorageException("File upload failed. " + file.getOriginalFilename(), ioException);
            }
        }).exceptionally(exception -> {
            System.err.println("Error uploading the file: " + exception.getMessage());
            return false;
        });
    }
}
