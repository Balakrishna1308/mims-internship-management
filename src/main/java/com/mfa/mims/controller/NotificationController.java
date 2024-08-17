package com.mfa.mims.controller;

import com.mfa.mims.entity.Notification;
import com.mfa.mims.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/process")
    public CompletableFuture<ResponseEntity<String>> processMessage(@RequestParam String message) {
        return notificationService.processNotificationMessage(message)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(500).build());
    }

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<Notification>> createNotification(@RequestBody Notification notification) {
        return notificationService.saveNotification(notification)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(500).build());
    }
}