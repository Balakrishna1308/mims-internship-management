package com.mfa.mims.controller;

import com.mfa.mims.entity.Task;
import com.mfa.mims.repository.TaskRepository;
import com.mfa.mims.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task)
    {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }


    @GetMapping("/{id}/statusAsChar")
    public ResponseEntity<String> getStatusAsChar(@PathVariable Long id)
    {
        String priorityAsChar = taskService.getTaskStatusCodeAsChar(id);
        return ResponseEntity.ok(priorityAsChar);
    }

    @GetMapping("/{id}/priorityAsChar")
    public ResponseEntity<String> getPriorityAsChar(@PathVariable Long id)
    {
        return ResponseEntity.ok(taskService.getTaskPriorityLevelAsChar(id));
    }

    @GetMapping("/{id}/timeSpentAsFloat")
    public ResponseEntity<Float> getTimeSpentAsFloat(@PathVariable Long id)
    {
        float timeSpentAsFloat = taskService.getTaskTimeSpentAsFloat(id);
        return ResponseEntity.ok(timeSpentAsFloat);
    }
}
