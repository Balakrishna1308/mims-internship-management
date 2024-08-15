package com.mfa.mims.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
@Data
@NoArgsConstructor
public class Progress {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "trainee_id", nullable = false)
    private String traineeId;

    @Column(name = "task", nullable = false)
    private String task;

    @Column(name = "completion_percentage", nullable = false)
    private Double completionPercentage;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

}
