package com.mfa.mims.service;

import com.mfa.mims.entity.TraineeJoiningDetails;

import java.util.List;
import java.util.Optional;

public interface TraineeJoiningDetailsService {
    TraineeJoiningDetails createTraineeJoiningDetails(TraineeJoiningDetails traineeJoiningDetails);
    List<TraineeJoiningDetails> getAllTraineeJoiningDetails();
}
