//package com.mfa.mims.controller;
//
//import com.mfa.mims.service.FileService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.util.concurrent.CompletableFuture;
//
//@RestController
//@RequestMapping("/api/files")
//@RequiredArgsConstructor
//public class FileController {
//
//    private final FileService fileService;
//
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile)
//    {
//        try
//        {
//            File convertedFile = convertMultipartFileToFile(multipartFile);
//            CompletableFuture<Boolean> uploadResult = fileService.uploadFile(convertedFile);
//
//            uploadResult.thenAccept(success->
//            {
//                if (success)
//                {
//                    System.out.println("File upload success");
//                }
//                else
//                {
//                    System.out.println("File upload failed");
//                }
//            });
//
//            return ResponseEntity.ok("File upload is in progress");
//
//        }
//        catch (Exception exception)
//        {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File conversion " +
//                    "failed "+exception.getMessage());
//        }
//
//    }
//
//    private File convertMultipartFileToFile(MultipartFile multipartFile) throws Exception
//    {
//        File file = new File(multipartFile.getOriginalFilename());
//        multipartFile.transferTo(file);
//        return file;
//    }
//}





package com.mfa.mims.controller;

import com.mfa.mims.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        try {
            CompletableFuture<Boolean> uploadResult = fileService.uploadFile(multipartFile);

            uploadResult.thenAccept(success -> {
                if (success) {
                    System.out.println("File upload success");
                } else {
                    System.out.println("File upload failed");
                }
            });

            return ResponseEntity.ok("File upload is in progress");

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File upload failed: " + exception.getMessage());
        }
    }
}
