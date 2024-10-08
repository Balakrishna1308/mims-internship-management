package com.mfa.mims.service.impl;

import com.mfa.mims.entity.Feedback;
import com.mfa.mims.repository.FeedbackRepository;
import com.mfa.mims.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<String> findDistinctCategories() {
        return feedbackRepository.findDistinctCategories();
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public int getAverageRatingAsInt() {

        float totalRating = 0.0f;

        List<Feedback> feedbacks = feedbackRepository.findAll();
        for (Feedback feedback:feedbacks)
        {
            totalRating = totalRating + feedback.getRating();
        }

        float averageRating = totalRating/feedbacks.size();
        return (int) averageRating;

    }

}
