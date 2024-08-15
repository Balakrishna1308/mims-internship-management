package com.mfa.mims.service.impl;

import com.mfa.mims.entity.Progress;
import com.mfa.mims.repository.ProgressRepository;
import com.mfa.mims.service.ProgressService;
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

    @Async
    @Override
    public CompletableFuture<List<Progress>> getAllProgress() {

       return CompletableFuture.completedFuture(progressRepository.findAll());

    }

    @Async
    @Override
    public CompletableFuture<Optional<Progress>> getProgressById(Long id) {
        Optional<Progress> optionalProgress = progressRepository.findById(id);
        CompletableFuture<Optional<Progress>> getProgressById = CompletableFuture.completedFuture(optionalProgress);
        return getProgressById;
    }

    @Async
    @Override
    public CompletableFuture<List<Progress>> getProgressByTraineeId(String traineeId) {

        return CompletableFuture.completedFuture(progressRepository.findByTraineeId(traineeId));
    }


    @Async
    @Override
    public CompletableFuture<Progress> createProgress(Progress progress) {
        progress.setLastUpdated(LocalDateTime.now());
        return CompletableFuture.completedFuture(progressRepository.save(progress));

    }


    @Async
    @Override
    public CompletableFuture<Progress> updateProgress(Long id, Progress progressDetails) {

        return progressRepository.findById(id)
                .map(progress ->
                        {
                            progress.setTask(progressDetails.getTask());
                            progress.setLastUpdated(progressDetails.getLastUpdated());
                            progress.setCompletionPercentage(progressDetails.getCompletionPercentage());
                            return CompletableFuture.completedFuture(progressRepository.save(progress));

                        }
                        ).orElseGet(()->CompletableFuture.completedFuture(null
                ));
        //Need to check the null from the above

        }


    @Override
    public CompletableFuture<Void> deleteProgress(Long id) {
        progressRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
        //Need to check the null from the above
    }
}
