package com.examly.springapp.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity  // Maps the class with database
public class Feedback {

    @Id // Defines the primary Key
    @GeneratedValue(strategy= GenerationType.IDENTITY) // Auto-Generated ID
    private long feedbackId;

    
    private String feedbackText;

    private LocalDate date;

    @ManyToOne  // Establishes a many-to-one relationship with the User entity
    @JoinColumn(name = "userId", nullable = false) // Setting the foreign key name
    private User user;

    @ManyToOne  // Establishes a many-to-one relationship with the Property entity
    @JoinColumn(name = "propertyId", nullable = false) // Setting the foreign key name
    private Property property;

    
    private String category;

    // Generate setter and getter methods:
    public long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
