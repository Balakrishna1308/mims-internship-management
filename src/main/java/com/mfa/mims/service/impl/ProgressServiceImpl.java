package com.mfa.mims.service.impl;
import org.springframework.transaction.annotation.Transactional;


import com.mfa.mims.entity.Progress;
import com.mfa.mims.repository.ProgressRepository;
import com.mfa.mims.service.ProgressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ProgressServiceImpl implements ProgressService {


    @Autowired
    private ProgressRepository progressRepository;


    private static final Logger logger = LoggerFactory.getLogger(ProgressServiceImpl.class);

    @Async("taskExecutor")
    @Override
    public CompletableFuture<List<Progress>> getAllProgress() {

        return CompletableFuture.completedFuture(progressRepository.findAll());

    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Optional<Progress>> getProgressById(Long id) {
        Optional<Progress> optionalProgress = progressRepository.findById(id);
        CompletableFuture<Optional<Progress>> getProgressById = CompletableFuture.completedFuture(optionalProgress);
        return getProgressById;
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<List<Progress>> getProgressByTraineeId(String traineeId) {

        return CompletableFuture.completedFuture(progressRepository.findByTraineeId(traineeId));
    }


//    @Async
//    @Override
//    public CompletableFuture<Progress> createProgress(Progress progress) {
//        progress.setLastUpdated(LocalDateTime.now());
//        return CompletableFuture.completedFuture(progressRepository.save(progress));
//
//    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Progress> createProgress(Progress progress) {
        progress.setLastUpdated(LocalDateTime.now());
//        progress.setCompletionPercentage(0.0);
        return CompletableFuture.completedFuture(progressRepository.save(progress));
    }


//    @Async
//    @Override
//    public CompletableFuture<Progress> updateProgress(Long id, Progress progressDetails) {
//
//        return progressRepository.findById(id)
//                .map(progress ->
//                        {
//                            progress.setTask(progressDetails.getTask());
//                            progress.setLastUpdated(progressDetails.getLastUpdated());
//                            progress.setCompletionPercentage(progressDetails.getCompletionPercentage());
//                            return CompletableFuture.completedFuture(progressRepository.save(progress));
//
//                        }
//                        ).orElseGet(()->CompletableFuture.completedFuture(null
//                ));
//        //Need to check the null from the above
//
//        }

//    @Override
//    @Async
//    @Transactional
//    public CompletableFuture<Progress> updateProgress(Long id, Progress progressDetails, int totalTasks, int completeTasks) {
//
//        //Adding logging for debugging
//        logger.info("Updating progress for id: {}", id);
//        logger.info("Progress details: {}", progressDetails);
//
//
//        return CompletableFuture.supplyAsync(() -> {
//            Optional<Progress> optionalProgress = progressRepository.findById(id);
//            if (optionalProgress.isPresent()) {
//                Progress progress = optionalProgress.get();
//
//                //Setting the values from the progressDetails
//                progress.setTraineeId(progressDetails.getTraineeId());
//                progress.setTask(progressDetails.getTask());
//
//                //Calculate the progress percentage
//                double tasksProgressPercentage = calculateProgressPercentage(totalTasks, completeTasks);
//                progress.setCompletionPercentage(tasksProgressPercentage);
//
//                //Set the last updated time
//                progress.setLastUpdated(LocalDateTime.now()); // or use progressDetails.getLastUpdated()
//
//                //Log the calculated percentage
//                logger.info("Calculated completion percentage: {}", tasksProgressPercentage);
//
//                return progressRepository.save(progress);
//            } else {
//                throw new IllegalArgumentException("Progress not found for id: " + id);
//            }
//        }).exceptionally(ex->
//                {
//                    logger.error("Error updating progress percentage for id: {}", id, ex);
//                    throw new RuntimeException(ex);
//                });
//    }


    @Override
    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<Progress> updateProgress(Long id, Progress progressDetails, int totalTasks, int completeTasks) {

        //Adding logging for debugging
        logger.info("Updating progress for id: {}", id);
        logger.info("Progress details: {}", progressDetails);


        return CompletableFuture.supplyAsync(() -> {
            Optional<Progress> optionalProgress = progressRepository.findById(id);
            if (optionalProgress.isPresent()) {
                Progress progress = optionalProgress.get();

                //Widening casting from int to float, then to double for progress percentage calculation
                float totalTasksFloat = totalTasks;
                float completedTasksFloat = completeTasks;

                double progressPercentage = calculateProgressPercentage(totalTasksFloat, completedTasksFloat);

                //Setting the values from the progressDetails
                progress.setTraineeId(progressDetails.getTraineeId());
                progress.setTask(progressDetails.getTask());
                progress.setCompletionPercentage(progressPercentage);

                //Set the last updated time
                progress.setLastUpdated(LocalDateTime.now()); // or use progressDetails.getLastUpdated()

                //Log the calculated percentage
                logger.info("Calculated completion percentage: {}", progressPercentage);

                return progressRepository.save(progress);
            } else {
                throw new IllegalArgumentException("Progress not found for id: " + id);
            }
        }).exceptionally(ex ->
        {
            logger.error("Error updating progress percentage for id: {}", id, ex);
            throw new RuntimeException(ex);
        });
    }


    @Override
    @Async("taskExecutor")
    public CompletableFuture<Boolean> deleteProgress(Long id) {
        Optional<Progress> optionalProgress = progressRepository.findById(id);
        if (optionalProgress.isPresent())
            return CompletableFuture.completedFuture(true);
        else
            return CompletableFuture.completedFuture(false);
    }




    @Override
    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<Progress> updateAndRoundCompletionPercentage(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<Progress> optionalProgress = progressRepository.findById(id);
            if (optionalProgress.isPresent()) {
                Progress progress= optionalProgress.get();

                // Narrowing casting from double to int
                double completionPercentage= progress.getCompletionPercentage();

//                int roundedPercentage= (int) Math.round(completionPercentage);

                // Round to two decimal places
                double roundedPercentage= Math.round(completionPercentage * 100.0) / 100.0;



                // Narrowing cast to int// Update the progress entity with the rounded value
                progress.setCompletionPercentage((double) roundedPercentage);
                // Save as double in the database
                return progressRepository.save(progress); // Save the updated entity to the database
            } else {
                throw new IllegalArgumentException("Progress not found for id: " + id);
            }
        });
    }






//    @Override
//    @Async
//    @Transactional
//    public CompletableFuture<Integer> getRoundedCompletionPercentage(Long id) {
//        return CompletableFuture.supplyAsync(() -> {
//            Optional<Progress> optionalProgress = progressRepository.findById(id);
//            if (optionalProgress.isPresent()) {
//                Progress progress= optionalProgress.get();
//
//                // Narrowing casting from double to int
//                double completionPercentage= progress.getCompletionPercentage();
//                int roundedPercentage= (int) Math.round(completionPercentage); // Narrowing cast to int
//                return roundedPercentage;
//            } else {
//                throw new IllegalArgumentException("Progress not found for id: " + id);
//            }
//        });
//    }







//    private double calculateProgressPercentage(int totalTasks, int completedTasks)
//    {
//        if (totalTasks==0)
//        throw new IllegalArgumentException("Total tasks cannot be zero");
//
//        double totalTasksInDouble = (double) totalTasks;
//        double completedTasksInDouble = (double) completedTasks;
//        return (completedTasksInDouble/totalTasksInDouble) * 100;
//    }




    private double calculateProgressPercentage(float totalTasks, float completedTasks)
    {
        if (totalTasks==0)
        {
            return 0.0;
        }

        //Calculate percentage with accurate double values
        return (double) (completedTasks/totalTasks) * 100;
    }


}
