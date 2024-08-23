package com.mfa.mims.service.impl;

import com.mfa.mims.entity.TraineeJoiningDetails;
import com.mfa.mims.repository.TraineeJoiningDetailsRepository;
import com.mfa.mims.service.TraineeJoiningDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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




    @Override
    public Optional<TraineeJoiningDetails> getTraineeJoiningDetailsById(Long id) {
        return traineeJoiningDetailsRepository.findById(id);
    }

    @Override
    public void deleteTraineeJoiningDetailsById(Long id) {
        traineeJoiningDetailsRepository.deleteById(id);
    }




    @Override
    public Optional<TraineeJoiningDetails> updateTraineeJoiningDetails(Long id, TraineeJoiningDetails traineeJoiningDetails) {
        return traineeJoiningDetailsRepository.findById(id)
                .map(existingDetails->
                        {
                            existingDetails.setTraineeId(traineeJoiningDetails.getTraineeId());
                            existingDetails.setJoiningDate(traineeJoiningDetails.getJoiningDate());
                            existingDetails.setTraineeName(traineeJoiningDetails.getTraineeName());
                            existingDetails.setCourseName(traineeJoiningDetails.getCourseName());
                            return traineeJoiningDetailsRepository.save(existingDetails);

                        }

                );
    }




}
