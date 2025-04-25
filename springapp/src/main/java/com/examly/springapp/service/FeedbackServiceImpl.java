package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.FeedbackNotFoundException;
import com.examly.springapp.exceptions.InvalidFeedbackException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;  // Updated variable name

    @Autowired
    private UserRepo userRepo;

    /**
     * Creates a new feedback entry.
     * Throws an exception if validation fails.
     */
    public Feedback createFeedback(Feedback feedback) {
        if (feedback == null) {
            throw new InvalidFeedbackException("Feedback cannot be null.");
        }
        if (feedback.getFeedbackText() == null || feedback.getFeedbackText().trim().isEmpty()) {
            throw new InvalidFeedbackException("Feedback text cannot be empty.");
        }
        return feedbackRepo.save(feedback);
    }

    /**
     * Retrieves feedback by ID.
     * Throws an exception if feedback is not found.
     */
    public Feedback getFeedbackById(Long feedbackId) {
        if (feedbackId == null) {
            throw new InvalidFeedbackException("Feedback ID cannot be null.");
        }
        return feedbackRepo.findById(feedbackId)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found with ID: " + feedbackId));
    }

    /**
     * Retrieves all feedbacks.
     * Throws an exception if no feedback entries exist.
     */
    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbackList = feedbackRepo.findAll();
        if (feedbackList.isEmpty()) {
            throw new FeedbackNotFoundException("No feedback available.");
        }
        return feedbackList;
    }

    /**
     * Retrieves feedbacks by User ID.
     * Throws an exception if user does not exist or no feedbacks exist for the given User ID.
     */
    public List<Feedback> getFeedbacksByUserId(Long userId) {
        if (userId == null) {
            throw new InvalidFeedbackException("User ID cannot be null.");
        }
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new FeedbackNotFoundException("User not found with ID: " + userId));
        
        List<Feedback> feedbackList = feedbackRepo.findByUser(user);
        if (feedbackList.isEmpty()) {
            throw new FeedbackNotFoundException("No feedback found for user ID: " + userId);
        }
        return feedbackList;
    }

    /**
     * Deletes a feedback entry by ID.
     * Throws an exception if feedback ID is null or feedback is not found.
     */
    public boolean deleteFeedback(Long feedbackId) {
        if (feedbackId == null) {
            throw new InvalidFeedbackException("Feedback ID cannot be null.");
        }
    
        // Retrieve feedback or throw exception if not found
        Feedback feedback = feedbackRepo.findById(feedbackId)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found with ID: " + feedbackId));
    
        // Delete feedback if found
        feedbackRepo.delete(feedback);
        return true;
    }
    
}
