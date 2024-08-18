package com.mfa.mims.controller;

import com.mfa.mims.entity.Feedback;
import com.mfa.mims.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;


    @PostMapping("/add")
    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback)
    {
        return ResponseEntity.ok(feedbackService.addFeedback(feedback));
    }


    @GetMapping("/categories")
    public ResponseEntity<List<String>> getDistinctCategories()
    {
        return ResponseEntity.ok(feedbackService.findDistinctCategories());
    }
}
