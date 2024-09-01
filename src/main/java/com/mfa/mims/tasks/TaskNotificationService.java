package com.mfa.mims.tasks;

import com.mfa.mims.entity.Task;

import java.util.function.Consumer;

public class TaskNotificationService {

    //Method to process task assignment using Consumer functional interface
    public void processTaskAssignment(Task task)
    {
        //Consumer to send the notification
        Consumer<Task> sendNotification = this::sendTaskNotification;

        //Consumer to log the task assignment
        Consumer<Task> logTaskAssignment = this::logTaskAssignment;

        //Chain the Consumer using andThen method - send task notification
        //and log task assignment
        sendNotification.andThen(logTaskAssignment).accept(task);
    }

    //Method to send a task notification to the user
    private void sendTaskNotification(Task task)
    {
        //Logic to send task notification to the user
        System.out.println("Notification sent to user for the task: " + task.getTaskName() + " - "+task.getTaskDescription());
    }

    //Method to log the task assignment
    private void logTaskAssignment(Task task)
    {
        //Logic to log the task assignment
        System.out.println("Task assignment logged for the task: "+task.getTaskName()+ " - "+task.getTaskDescription());
    }
}
