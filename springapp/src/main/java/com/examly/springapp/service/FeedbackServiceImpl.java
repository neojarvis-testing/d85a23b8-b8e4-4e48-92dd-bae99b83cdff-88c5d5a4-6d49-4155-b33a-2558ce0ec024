package com.examly.springapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

private Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);


    @Autowired
    private FeedbackRepo feedbackRepo;  // Updated variable name

    @Autowired
    private UserRepo userRepo;

    /**
     * Creates a new feedback entry.
     * Throws an exception if validation fails.
     */
    public Feedback createFeedback(Feedback feedback) {
        logger.info("Attempting to create new feedback");

        if (feedback == null) {
            logger.error("Feedback creation failed: Feedback is null.");

            throw new InvalidFeedbackException("Feedback cannot be null.");
        }
        if (feedback.getFeedbackText() == null || feedback.getFeedbackText().trim().isEmpty()) {
            logger.warn("Feedback creation failed: Empty feedback text.");
            throw new InvalidFeedbackException("Feedback text cannot be empty.");
        }

        logger.info("Feedback created successfully.");
        return feedbackRepo.save(feedback);
    }

    /**
     * Retrieves feedback by ID.
     * Throws an exception if feedback is not found.
     */
    public Feedback getFeedbackById(Long feedbackId) {
        logger.info("Fetching feedback with ID: {}", feedbackId);

        if (feedbackId == null) {
            logger.error("Invalid feedback ID: Null value provided.");

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
        logger.info("Get All the Feedbacks");
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
        logger.info("Fetching feedback for user ID: {}", userId);
        if (userId == null) {
            logger.error("Invalid user ID: Null value provided.");
            throw new InvalidFeedbackException("User ID cannot be null.");
        }
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new FeedbackNotFoundException("User not found with ID: " + userId));
        
        List<Feedback> feedbackList = feedbackRepo.findByUser(user);
        if (feedbackList.isEmpty()) {
            logger.warn("No feedback found for user ID: {}", userId);

            throw new FeedbackNotFoundException("No feedback found for user ID: " + userId);
        }
        return feedbackList;
    }

    /**
     * Deletes a feedback entry by ID.
     * Throws an exception if feedback ID is null or feedback is not found.
     */
    public boolean deleteFeedback(Long feedbackId) {
        logger.info("Attempting to delete feedback with ID: {}", feedbackId);


        if (feedbackId == null) {
            logger.error("No Feedback provided value provided.");

            throw new InvalidFeedbackException("Feedback ID cannot be null.");
        }

    
        // Retrieve feedback or throw exception if not found
        Feedback feedback = feedbackRepo.findById(feedbackId).orElse(null);
        if(feedback==null){
            throw new FeedbackNotFoundException("Feedback not found with ID: " + feedbackId);
        }
                
    
        // Delete feedback if found
        feedbackRepo.delete(feedback);
        logger.info("Feedback deleted successfully for ID: {}", feedbackId);

        return true;
    }
    
}
