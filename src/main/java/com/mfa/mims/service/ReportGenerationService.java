package com.mfa.mims.service;

import java.util.concurrent.CompletableFuture;

public interface ReportGenerationService {
    CompletableFuture<String> generateReport(String reportType);
}
