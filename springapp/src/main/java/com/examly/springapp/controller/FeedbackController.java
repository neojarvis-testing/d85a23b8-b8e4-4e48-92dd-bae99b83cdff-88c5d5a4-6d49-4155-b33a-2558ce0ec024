package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.exceptions.InvalidFeedbackException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackServiceImpl;

@RequestMapping("/api/feedback")
@RestController
public class FeedbackController {
    
    @Autowired
    FeedbackServiceImpl feedbackService;

    // Create a new feedback entry
    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
    try {
        Feedback savedFeedback = feedbackService.createFeedback(feedback);
        return ResponseEntity.status(201).body(savedFeedback); // Returns 201 Created on success
    } 
    catch (InvalidFeedbackException e) {
        return ResponseEntity.status(409).body(null); // Returns 400 Bad Request if feedback is invalid
    }
    }

    
    // Get feedback by ID
    @GetMapping("/{feedbackId}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long feedbackId) {
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        return feedback != null
            ? ResponseEntity.ok(feedback) // Returns 200 OK if feedback is found
            : ResponseEntity.status(404).body(null); // Returns 404 if feedback is not found
    }

    // Get all feedback entries
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbackList = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbackList); // Returns 200 OK
    }


    // Get feedback by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByUserId(@PathVariable Long userId) {
        List<Feedback> feedbackList = feedbackService.getFeedbacksByUserId(userId);
        return feedbackList.isEmpty()
            ? ResponseEntity.status(404).body(List.of()) // Returns empty list with 404 Not Found if no feedback exists
            : ResponseEntity.ok(feedbackList); // Returns 200 OK if feedback exists
    }

    // Delete feedback by ID
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long feedbackId) {
        boolean isDeleted = feedbackService.deleteFeedback(feedbackId);
        return isDeleted
            ? ResponseEntity.ok("Feedback deleted successfully.") // Returns 200 OK on successful deletion
            : ResponseEntity.status(404).body("Feedback not found."); // Returns 404 if feedback doesn't exist
    }
}
