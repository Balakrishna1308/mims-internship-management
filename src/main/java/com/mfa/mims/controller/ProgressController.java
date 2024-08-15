package com.mfa.mims.controller;

import com.mfa.mims.entity.Progress;
import com.mfa.mims.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/progress")
public class ProgressController{


    @Autowired
    private ProgressService progressService;


    @GetMapping
    public CompletableFuture<ResponseEntity<List<Progress>>> getAllProgress()
    {
        return progressService.getAllProgress().thenApply(ResponseEntity::ok);
    }


    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Progress>> getProgressById(@PathVariable Long id)
    {
        return progressService.getProgressById(id).
                thenApply(progress -> progress.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build()));

    }


    @GetMapping("/trainee/{traineeId}")
    public CompletableFuture<ResponseEntity<List<Progress>>> getProgressByTraineeId(@PathVariable String traineeId)
    {
        return progressService.getProgressByTraineeId(traineeId).thenApply(ResponseEntity::ok);
    }


    @PostMapping
    public CompletableFuture<ResponseEntity<Progress>> createProgress(@RequestBody Progress progress)
    {
        return progressService.createProgress(progress).thenApply(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Progress>> updateProgress(@PathVariable Long id, Progress progressDetails) {
        return progressService.updateProgress(id, progressDetails).thenApply(progress ->
        {
            if (progress == null)
                return ResponseEntity.notFound().build();
            else return ResponseEntity.ok(progress);
        });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteProgress(@PathVariable Long id)
    {
        return progressService.deleteProgress(id).thenApply(v->ResponseEntity.noContent().build());
    }
}