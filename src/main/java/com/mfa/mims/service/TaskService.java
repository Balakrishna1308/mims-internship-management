package com.mfa.mims.service;

import com.mfa.mims.entity.Task;

import java.util.List;

public interface TaskService {
    String getTaskStatusCodeAsChar(Long taskId);
    String getTaskPriorityLevelAsChar(Long taskId);
    float getTaskTimeSpentAsFloat(Long taskId);
    Task createTask(Task task);
    List<Task> getAllTasks();
    byte getTaskPriorityAsByte(Long taskId);
}
