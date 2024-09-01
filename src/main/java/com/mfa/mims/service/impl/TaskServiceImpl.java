package com.mfa.mims.service.impl;

import com.mfa.mims.entity.Task;
import com.mfa.mims.repository.TaskRepository;
import com.mfa.mims.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public String getTaskStatusCodeAsChar(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("Task not found"));
        return String.valueOf(task.getStatusAsChar());
    }

    @Override
    public String getTaskPriorityLevelAsChar(Long taskId) {
       Task task = taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("Task not found"));
       return String.valueOf(task.getPriorityAsChar());
    }

    @Override
    public float getTaskTimeSpentAsFloat(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException(("Task not found")));
        return task.getTimeSpentAsFloat();
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public byte getTaskPriorityAsByte(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()->new RuntimeException("Task not found"));
        return task.getPriorityAsByte();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
}
