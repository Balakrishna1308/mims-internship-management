package com.mfa.mims.service;

import com.mfa.mims.entity.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {
    Feedback addFeedback(Feedback feedback);
    List<String> findDistinctCategories();
    List<Feedback> getAllFeedbacks();
    int getAverageRatingAsInt();
}
