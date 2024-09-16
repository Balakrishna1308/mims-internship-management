package com.mfa.mims.service;

import com.mfa.mims.entity.Progress;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ProgressService {
    CompletableFuture<List<Progress>> getAllProgress();

    CompletableFuture<Optional<Progress>> getProgressById(Long id);

    CompletableFuture<List<Progress>> getProgressByTraineeId(String traineeId);

    CompletableFuture<Progress> createProgress(Progress progress);

    CompletableFuture<Progress> updateProgress(Long id, Progress progressDetails, Integer totalTasks, Integer completedTasks);

    CompletableFuture<Boolean> deleteProgress(Long id);

//    CompletableFuture<Integer> getRoundedCompletionPercentage(Long id);
    CompletableFuture<Progress> updateAndRoundCompletionPercentage(Long id);

    //Method to check for existing traineeId
    CompletableFuture<Boolean> doesTraineeIdExist(String traineeId);

}
