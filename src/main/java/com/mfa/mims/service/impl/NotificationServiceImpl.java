package com.mfa.mims.service.impl;

import com.mfa.mims.entity.Notification;
import com.mfa.mims.repository.NotificationRepository;
import com.mfa.mims.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.UnaryOperator;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Async("taskExecutor")
    public CompletableFuture<String> processNotificationMessage(String message, boolean applyTransformation) {
        // Same implementation as before for processing the message
        UnaryOperator<String> toUpperCase = String::toUpperCase;
        UnaryOperator<String> addPrefix = msg -> "IMPORTANT: " + msg;
        Function<String, String> trimAndPrefix = addPrefix.compose(String::trim);

        //First, converting to upper case, then trimming, and adding prefix
        Function<String, String> toUpperTrimAndPrefix = toUpperCase.andThen(trimAndPrefix);

        //For nothing to be transformed, use identity operator
        UnaryOperator<String> identityOperator = UnaryOperator.identity();

        //Choose the transformation based on the flag
        Function<String, String> selectedOperator = applyTransformation ? toUpperTrimAndPrefix : identityOperator;

        String transformedMessage = selectedOperator.apply(message);

//      return CompletableFuture.completedFuture(transformedMessage);
        return CompletableFuture.completedFuture("Processed: "+transformedMessage);
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Notification> saveNotification(Notification notification) {
        notification.setTimestamp(LocalDateTime.now());
        return CompletableFuture.completedFuture(notificationRepository.save(notification));
    }
}
