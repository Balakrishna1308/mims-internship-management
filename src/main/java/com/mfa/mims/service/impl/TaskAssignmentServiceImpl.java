package com.mfa.mims.service.impl;

import com.mfa.mims.entity.Task;
import com.mfa.mims.tasks.TaskNotificationService;
import org.springframework.stereotype.Service;

@Service
public class TaskAssignmentServiceImpl {

    private final TaskNotificationService taskNotificationService;

    public TaskAssignmentServiceImpl(TaskNotificationService taskNotificationService) {
        this.taskNotificationService = taskNotificationService;
    }

    public void assignTask(Task task)
    {
        taskNotificationService.processTaskAssignment(task);
    }
}
