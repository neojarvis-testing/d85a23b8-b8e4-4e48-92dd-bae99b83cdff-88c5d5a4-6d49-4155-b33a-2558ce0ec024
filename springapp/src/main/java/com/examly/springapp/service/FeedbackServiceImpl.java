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
public class FeedbackServiceImpl {

    @Autowired
    FeedbackRepo feedbackRepository;

    @Autowired
    UserRepo userRepo;

    /**
     * Creates a new feedback entry.
     * Throws an exception if validation fails.
     */
    public Feedback createFeedback(Feedback feedback) {
        if (feedback == null || feedback.getFeedbackText().isEmpty()) {
            throw new InvalidFeedbackException("Feedback text cannot be empty.");
        }
        return feedbackRepository.save(feedback);
    }

    /**
     * Retrieves feedback by ID.
     * Throws an exception if feedback is not found.
     */
    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found with ID: " + feedbackId));
    }

    /**
     * Retrieves all feedbacks.
     */
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll(); 
    }
    
    

    /**
     * Retrieves feedbacks by User ID.
     * Throws an exception if no feedbacks exist for the given User ID.
     */
    public List<Feedback> getFeedbacksByUserId(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new FeedbackNotFoundException("User not found with ID: " + userId));
        List<Feedback> feedbackList = feedbackRepository.findByUser(user);
        if (feedbackList.isEmpty()) {
            throw new FeedbackNotFoundException("No feedback found for user ID: " + userId);
        }
        return feedbackList;
    }
    

    /**
     * Deletes a feedback entry by ID.
     * Throws an exception if feedback is not found.
     */
    public boolean deleteFeedback(Long feedbackId) {
        if (!feedbackRepository.existsById(feedbackId)) {
            throw new FeedbackNotFoundException("Feedback not found with ID: " + feedbackId);
        }
        feedbackRepository.deleteById(feedbackId);
        return true;
    }
}
