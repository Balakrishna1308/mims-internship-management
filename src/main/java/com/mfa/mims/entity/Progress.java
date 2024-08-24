package com.mfa.mims.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
@Data
@NoArgsConstructor
public class Progress {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trainee_id", nullable = false)
    private String traineeId;

    @Column(name = "task", nullable = false)
    private String task;

    @Column(name = "completion_percentage", nullable = false)
//    private Double completionPercentage;
    private Double completionPercentage = 0.0;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAT;

}
