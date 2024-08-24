package com.mfa.mims.service;

import com.mfa.mims.entity.Task;

public interface TaskService {
    String getTaskStatusCodeAsChar(Long taskId);
    String getTaskPriorityLevelAsChar(Long taskId);
    float getTaskTimeSpentAsFloat(Long taskId);
    Task createTask(Task task);
}
