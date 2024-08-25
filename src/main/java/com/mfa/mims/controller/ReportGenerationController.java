package com.mfa.mims.controller;

import com.mfa.mims.service.ReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/reports")
public class ReportGenerationController {

    @Autowired
    private ReportGenerationService reportGenerationService;

    @PostMapping("/generate")
    public CompletableFuture<ResponseEntity<String>> generateReport(@RequestParam String reportType)
    {
        return reportGenerationService.generateReport(reportType)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex-> ResponseEntity.status(500).body("Report generation failed"));
    }
}
