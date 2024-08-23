package com.mfa.mims.controller;

import com.mfa.mims.entity.TraineeJoiningDetails;
import com.mfa.mims.service.TraineeJoiningDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forms-trainee-joining-details")
public class TraineeJoiningDetailsController {

    @Autowired
    private TraineeJoiningDetailsService traineeJoiningDetailsService;

    @PostMapping
    public ResponseEntity<TraineeJoiningDetails> createTraineeJoiningDetails(@RequestBody TraineeJoiningDetails traineeJoiningDetails)
    {
        return ResponseEntity.ok(traineeJoiningDetailsService.createTraineeJoiningDetails(traineeJoiningDetails));
    }

    @GetMapping
    public ResponseEntity<List<TraineeJoiningDetails>> getAllTraineeJoiningDetails()
    {
        List<TraineeJoiningDetails> listOfTraineeJoiningDetails = traineeJoiningDetailsService.getAllTraineeJoiningDetails();
        return ResponseEntity.ok(listOfTraineeJoiningDetails);
    }

}
