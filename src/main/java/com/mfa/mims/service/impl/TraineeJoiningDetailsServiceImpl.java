package com.mfa.mims.service.impl;

import com.mfa.mims.entity.TraineeJoiningDetails;
import com.mfa.mims.repository.TraineeJoiningDetailsRepository;
import com.mfa.mims.service.TraineeJoiningDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeJoiningDetailsServiceImpl implements TraineeJoiningDetailsService {

    @Autowired
    private TraineeJoiningDetailsRepository traineeJoiningDetailsRepository;

//    @Bean
//    public TraineeJoiningDetailsService traineeService()
//    {
//        return new TraineeJoiningDetailsServiceImpl();
//    }

    @Override
    public TraineeJoiningDetails createTraineeJoiningDetails(TraineeJoiningDetails traineeJoiningDetails) {
        return traineeJoiningDetailsRepository.save(traineeJoiningDetails);
    }

    @Override
    public List<TraineeJoiningDetails> getAllTraineeJoiningDetails() {
        return traineeJoiningDetailsRepository.findAll();
    }
}
