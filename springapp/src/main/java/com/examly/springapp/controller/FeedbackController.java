package com.examly.springapp.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.dtos.FeedbackDTO;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackServiceImpl;
import jakarta.validation.Valid;

/**
 * REST Controller for managing feedback-related operations.
 * Provides endpoints for creating, retrieving, and deleting feedback entries.
 */
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    
    private final FeedbackServiceImpl feedbackService;

    /**
     * Constructor-based dependency injection for the feedback service.
     * @param feedbackService Service for handling feedback operations
     */
    public FeedbackController(FeedbackServiceImpl feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * Creates a new feedback entry.
     * @param feedbackDTO Data transfer object containing feedback details
     * @return ResponseEntity containing the created feedback and HTTP status
     */
    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackService.createFeedback(feedbackDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedback);
    }

    /**
     * Retrieves a feedback entry by its ID.
     * @param feedbackId ID of the feedback entry
     * @return ResponseEntity containing the feedback details or error message
     */
    @GetMapping("/{feedbackId}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long feedbackId) {
        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.getFeedbackById(feedbackId));
    }

    /**
     * Retrieves all feedback entries.
     * @return ResponseEntity containing a list of feedback entries or error message
     */
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.getAllFeedbacks());
    }

    /**
     * Retrieves all feedback entries provided by a specific user.
     * @param userId ID of the user whose feedback entries need to be retrieved
     * @return ResponseEntity containing a list of feedback entries or error message
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByUserId(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.getFeedbacksByUserId(userId));
    }

    /**
     * Deletes a feedback entry by its ID.
     * @param feedbackId ID of the feedback entry to delete
     * @return ResponseEntity containing a success message
     */
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long feedbackId) {
        return ResponseEntity.status(HttpStatus.OK).body("Feedback deleted successfully.");
    }
}
