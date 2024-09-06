package com.mfa.mims.controller;

import com.mfa.mims.entity.Progress;
import com.mfa.mims.service.ProgressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {


    @Autowired
    private ProgressService progressService;


    private static final Logger logger = LoggerFactory.getLogger(ProgressController.class);


    @GetMapping
    public CompletableFuture<ResponseEntity<List<Progress>>> getAllProgress() {
        return progressService.getAllProgress().thenApply(ResponseEntity::ok);
    }


    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Progress>> getProgressById(@PathVariable Long id) {
        return progressService.getProgressById(id).
                thenApply(progress -> progress.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()));

    }


    @GetMapping("/trainee/{traineeId}")
    public CompletableFuture<ResponseEntity<List<Progress>>> getProgressByTraineeId(@PathVariable String traineeId) {
        return progressService.getProgressByTraineeId(traineeId).thenApply(ResponseEntity::ok);
    }


    @PostMapping
    public CompletableFuture<ResponseEntity<Progress>> createProgress(@RequestBody Progress progress) {
        return progressService.createProgress(progress).thenApply(ResponseEntity::ok);
    }

//    @PutMapping("/{id}")
//    public CompletableFuture<ResponseEntity<Progress>> updateProgress(@PathVariable Long id, Progress progressDetails) {
//        return progressService.updateProgress(id, progressDetails).thenApply(progress ->
//        {
//            if (progress == null)
//                return ResponseEntity.notFound().build();
//            else return ResponseEntity.ok(progress);
//        });
//    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Progress>> updateProgress(@PathVariable Long id, @RequestBody Progress progressDetails, @RequestParam int totalTasks, @RequestParam int completedTasks) {
        //Adding logging for debugging
        logger.info("Received request for updating the progress with id: {}", id);
        logger.info("Progress details from request: {}", progressDetails);

//        return progressService.updateProgress(id, progressDetails, totalTasks, completedTasks)
//                .thenApply(updatedProgress -> ResponseEntity.ok(updatedProgress))
//                .exceptionally(ex -> ResponseEntity.status(500).build());
            return progressService.updateProgress(id, progressDetails, totalTasks, completedTasks)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex ->
                {
                    logger.error("Error in controller for updating progress for id: {}", id, ex);

                      return ResponseEntity.notFound().build();
                });

    }




    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteProgress(@PathVariable Long id)
    {
        return progressService.deleteProgress(id).thenApply(v->ResponseEntity.noContent().build());
    }


    @PutMapping("/{id}/round-completion")
    public CompletableFuture<ResponseEntity<Progress>> updateAndRoundCompletionPercentage(@PathVariable Long id) {
        return progressService.updateAndRoundCompletionPercentage(id)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(500).build());
    }
  }

