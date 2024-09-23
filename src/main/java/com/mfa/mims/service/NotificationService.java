package com.mfa.mims.service;

import com.mfa.mims.entity.Notification;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface NotificationService {
    CompletableFuture<String> processNotificationMessage(String message, boolean applyTransformation);
    CompletableFuture<Notification> saveNotification(Notification notification);


    CompletableFuture<Boolean> saveAllNotifications(Collection<? extends Notification> notifications);


}