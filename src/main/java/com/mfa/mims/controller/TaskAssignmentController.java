package com.mfa.mims.controller;

import com.mfa.mims.entity.Task;
import com.mfa.mims.service.TaskService;
import com.mfa.mims.service.impl.TaskAssignmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskAssignmentController {

    @Autowired
    private TaskService taskService;

    private final TaskAssignmentServiceImpl taskAssignmentServiceImpl;

    public TaskAssignmentController(TaskAssignmentServiceImpl taskAssignmentServiceImpl) {
        this.taskAssignmentServiceImpl = taskAssignmentServiceImpl;
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<String> assignATask(@PathVariable Long id,
                                              @RequestParam String taskName,
                                              @RequestParam String taskDescription)
    {

        Optional<Task> existingTask = taskService.getTaskById(id);

        if (existingTask.isPresent())
        {
            Task task = new Task(taskName, taskDescription);
            taskAssignmentServiceImpl.assignTask(task);
            return ResponseEntity.ok("Task assigned successfully, the details of " +
                    "which are as follows: "+"\nTask Name: "+taskName+"\nTask Description: "+taskDescription);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task assignment" +
                    "failed: "+"Task with id "+id+" not found");
        }
    }

}
