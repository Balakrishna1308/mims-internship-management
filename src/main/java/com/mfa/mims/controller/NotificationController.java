package com.mfa.mims.controller;

import com.mfa.mims.entity.Notification;
import com.mfa.mims.repository.NotificationRepository;
import com.mfa.mims.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/transform")
    public CompletableFuture<ResponseEntity<String>> processMessage
            (@RequestParam String message, @RequestParam boolean applyTransformation ) {
        return notificationService.processNotificationMessage(message, applyTransformation)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(500).build());
    }

//    @PostMapping("/create")
//    public CompletableFuture<ResponseEntity<Notification>> createNotification(@RequestBody Notification notification) {
//        return notificationService.saveNotification(notification)
//                .thenApply(ResponseEntity::ok)
//                .exceptionally(ex -> ResponseEntity.status(500).build());
//    }

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<String>> createNotification(@RequestBody Notification notification) {
        return notificationService.saveNotification(notification)
                .thenApply(addedSuccessfully->
                {
                    if (addedSuccessfully)
                    {
                       return ResponseEntity.status(HttpStatus.CREATED).body("Notification has been created successfully");
                    }
                    else
                    {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create notification");
                    }

                });
    }

    @PostMapping("/createAll")
    public CompletableFuture<ResponseEntity<String>> createAllNotifications(@RequestBody List<Notification> notifications)
    {
        CompletableFuture<Boolean> added = notificationService.saveAllNotifications(notifications);
        return added.thenApply(addedSuccessfully->
        {
            if (addedSuccessfully) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added notifications");
            }
            else
            {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create notification");
            }

        });
    }

    @GetMapping
    public CompletableFuture<List<Notification>> getAllNotifications()
    {
         return notificationService
                .getAllNotifications()
                 .thenApply(Function.identity());
    }
}