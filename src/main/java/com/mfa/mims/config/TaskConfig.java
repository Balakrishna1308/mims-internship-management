package com.mfa.mims.config;

import com.mfa.mims.tasks.TaskNotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {

    @Bean
    public TaskNotificationService taskNotificationService() {
        return new TaskNotificationService();
    }
}
