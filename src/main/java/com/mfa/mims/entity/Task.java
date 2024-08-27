package com.mfa.mims.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task")
//@Data
//@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte statusCode;
    private short priorityLevel;
    private int timeSpent;

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(name = "priority_char", nullable = false)
    private char priorityChar;

    @Transient
    private byte priorityAsByte;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public char getPriorityChar() {
        return priorityChar;
    }

    public void setPriorityChar(char priorityChar) {
        this.priorityChar = priorityChar;
    }




    //Widening type casting
    public char getStatusAsChar()
    {
        return (char) statusCode;
    }

    public char getPriorityAsChar()
    {
        return (char) priorityLevel;
    }

    public float getTimeSpentAsFloat()
    {
        return (float) timeSpent;
    }




    //Narrowing type casting

    public byte getPriorityAsByte()
    {
        return (byte) priorityChar;
    }



    public void setStatusCode(byte statusCode) {
        this.statusCode = statusCode;
    }

    public void setPriorityLevel(short priorityLevel) {
        this.priorityLevel = priorityLevel;
    }


    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }
}
