package com.examly.springapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.examly.springapp.dtos.FeedbackDTO;
import com.examly.springapp.dtos.RowMapper;
import com.examly.springapp.exceptions.FeedbackNotFoundException;
import com.examly.springapp.exceptions.InvalidFeedbackException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.Property;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.PropertyRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    // Logger for logging various events in the service
    private Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    // Repository dependencies for handling database operations
    private final FeedbackRepo feedbackRepo;
    private final UserRepo userRepo;
    private final PropertyRepo propertyRepo;

    /**
     * Constructor-based dependency injection for repositories.
     * @param feedbackRepo Repository for Feedback
     * @param userRepo Repository for Users
     * @param propertyRepo Repository for Properties
     */
    public FeedbackServiceImpl(FeedbackRepo feedbackRepo, UserRepo userRepo, PropertyRepo propertyRepo) {
        this.feedbackRepo = feedbackRepo;
        this.userRepo = userRepo;
        this.propertyRepo = propertyRepo;
    }
    
    /**
     * Creates a new feedback entry.
     * Validates input, maps DTO to entity, and saves feedback.
     * @param feedbackDTO Feedback data transfer object
     * @return The saved Feedback entity
     * @throws InvalidFeedbackException if feedback DTO is null or contains invalid data
     */
    public Feedback createFeedback(FeedbackDTO feedbackDTO) {
        logger.info("Attempting to create new feedback");
    
        // Validate FeedbackDTO
        if (feedbackDTO == null) {
            logger.error("Feedback creation failed: Feedback is null.");
            throw new InvalidFeedbackException("Feedback cannot be null.");
        }
        if (feedbackDTO.getFeedbackText() == null || feedbackDTO.getFeedbackText().trim().isEmpty()) {
            logger.warn("Feedback creation failed: Empty feedback text.");
            throw new InvalidFeedbackException("Feedback text cannot be empty.");
        }
    
        // Convert DTO to Entity
        Feedback feedback = RowMapper.mapToFeedbackDTO(feedbackDTO);
    
        // Fetch Property and User entities
        Property property = propertyRepo.findById(feedback.getProperty().getPropertyId()).orElse(null);
        User user = userRepo.findById(feedback.getUser().getUserId()).orElse(null);

        // Set Property and User entities before saving
        feedback.setProperty(property);
        feedback.setUser(user);
    
        // Save Feedback entity
        Feedback savedFeedback = feedbackRepo.save(feedback);
        logger.info("Feedback created successfully: {}", savedFeedback);
        
        return savedFeedback;
    }

    /**
     * Retrieves feedback by ID.
     * Throws an exception if feedback is not found.
     * @param feedbackId ID of the feedback entry
     * @return The Feedback entity if found
     * @throws FeedbackNotFoundException if feedback does not exist
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
     * Retrieves all feedback entries.
     * Throws an exception if no feedback entries exist.
     * @return List of all Feedback entries
     * @throws FeedbackNotFoundException if no feedback is available
     */
    public List<Feedback> getAllFeedbacks() {
        logger.info("Fetching all feedback entries");
        List<Feedback> feedbackList = feedbackRepo.findAll();

        if (feedbackList.isEmpty()) {
            throw new FeedbackNotFoundException("No feedback available.");
        }

        return feedbackList;
    }

    /**
     * Retrieves feedback entries by User ID.
     * Throws an exception if user does not exist or no feedbacks exist for the given User ID.
     * @param userId ID of the user
     * @return List of Feedback entries for the given user
     * @throws FeedbackNotFoundException if user or feedback is not found
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
     * @param feedbackId ID of the feedback entry
     * @return true if deletion was successful
     * @throws InvalidFeedbackException if feedback ID is null
     * @throws FeedbackNotFoundException if feedback does not exist
     */
    public boolean deleteFeedback(Long feedbackId) {
        logger.info("Attempting to delete feedback with ID: {}", feedbackId);

        if (feedbackId == null) {
            logger.error("Invalid feedback ID: Null value provided.");
            throw new InvalidFeedbackException("Feedback ID cannot be null.");
        }

        // Retrieve feedback or throw exception if not found
        Feedback feedback = feedbackRepo.findById(feedbackId).orElse(null);
        if (feedback == null) {
            throw new FeedbackNotFoundException("Feedback not found with ID: " + feedbackId);
        }

        // Delete feedback if found
        feedbackRepo.delete(feedback);
        logger.info("Feedback deleted successfully for ID: {}", feedbackId);

        return true;
    }
}
