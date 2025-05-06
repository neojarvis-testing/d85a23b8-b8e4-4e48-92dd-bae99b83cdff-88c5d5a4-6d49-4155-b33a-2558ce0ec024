package com.examly.springapp.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a feedback entity mapped to the database.
 * Feedback is provided by a user regarding a specific property.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    /**
     * Unique identifier for each feedback entry.
     * The ID is auto-generated using the identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedbackId;

    /**
     * Text content of the feedback provided by the user.
     */
    private String feedbackText;

    /**
     * Date when the feedback was submitted.
     */
    private LocalDate date;

    /**
     * Many-to-one relationship with the User entity.
     * Links feedback to the specific user who provided it.
     */
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    /**
     * Many-to-one relationship with the Property entity.
     * Links feedback to the specific property it is about.
     */
    @ManyToOne
    @JoinColumn(name = "propertyId", nullable = false)
    private Property property;

    /**
     * Category of the feedback (e.g., Positive, Negative, Suggestion).
     */
    private String category;
}
