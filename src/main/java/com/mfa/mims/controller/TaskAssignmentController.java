package com.mfa.mims.controller;

import com.mfa.mims.entity.Task;
import com.mfa.mims.service.impl.TaskAssignmentServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskAssignmentController {

    private final TaskAssignmentServiceImpl taskAssignmentServiceImpl;

    public TaskAssignmentController(TaskAssignmentServiceImpl taskAssignmentServiceImpl) {
        this.taskAssignmentServiceImpl = taskAssignmentServiceImpl;
    }

    @PutMapping("/{id}/assign")
    public String assignATask(@PathVariable Long id,
                              @RequestParam String taskName,
                             @RequestParam String taskDescription)
    {
        Task task = new Task(taskName, taskDescription);
        taskAssignmentServiceImpl.assignTask(task);
        return "Task assigned successfully...";
    }


}
