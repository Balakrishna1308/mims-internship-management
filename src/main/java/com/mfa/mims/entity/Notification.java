package com.mfa.mims.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trainee_id", nullable = false)
    private String traineeId;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
