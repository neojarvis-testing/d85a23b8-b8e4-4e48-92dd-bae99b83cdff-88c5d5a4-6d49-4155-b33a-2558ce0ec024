package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.dtos.FeedbackDTO;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackServiceImpl;

import jakarta.validation.Valid;

@RequestMapping("/api/feedback")
@RestController
public class FeedbackController {
    
    @Autowired
    private FeedbackServiceImpl feedbackservice; // Using the requested service name

    // Create a new feedback entry
     @PostMapping
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody  FeedbackDTO feedbackDTO) {
    	Feedback feedback = feedbackservice.createFeedback(feedbackDTO);
        return ResponseEntity.status(201).body(feedback); // Returns 201 Created
    }


    // Get feedback by ID
    @GetMapping("/{feedbackId}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long feedbackId) {
        return ResponseEntity.status(200).body(feedbackservice.getFeedbackById(feedbackId)); // Returns 200 OK
    }

    // Get all feedback entries
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.status(200).body(feedbackservice.getAllFeedbacks()); // Returns 200 OK
    }

    // Get feedback by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByUserId(@PathVariable Long userId) {
        return ResponseEntity.status(200).body(feedbackservice.getFeedbacksByUserId(userId)); // Returns 200 OK
    }

    // Delete feedback by ID
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long feedbackId) {
        boolean isDeleted=feedbackservice.deleteFeedback(feedbackId);
        return ResponseEntity.status(200).body("Feedback deleted successfully."); // Returns 200 OK
    }
}
