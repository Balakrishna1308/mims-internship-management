package com.mfa.mims.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte statusCode;
    private short priorityLevel;
    private int timeSpent;

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

}
