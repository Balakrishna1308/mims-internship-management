package com.mfa.mims.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "trainee_joining_details")
public class TraineeJoiningDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainee-joining-details-seq")
    @SequenceGenerator(
                        name = "trainee-joining-details-seq",
                        sequenceName = "trainee-joining-details-sequence",
                        allocationSize = 1
                      )
    private Long id;

    @Column(name = "trainee_name", nullable = false)
    private String traineeName;

    @Column(name = "trainee_id", nullable = false)
    private String traineeId;

    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Column(name = "course_name", nullable = false)
    private String courseName;

}
