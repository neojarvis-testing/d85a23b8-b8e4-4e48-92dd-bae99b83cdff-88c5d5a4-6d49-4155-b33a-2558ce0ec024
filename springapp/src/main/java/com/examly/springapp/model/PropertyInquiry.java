package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a property inquiry made by a user regarding a specific property.
 * This entity maps to the database and includes relationships with User and Property entities.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyInquiry {

    /**
     * Unique identifier for each inquiry.
     * The ID is auto-generated using the identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inquiryId;

    /**
     * Many-to-one relationship with the User entity.
     * Links each inquiry to a specific user.
     */
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    /**
     * Many-to-one relationship with the Property entity.
     * Links each inquiry to a specific property.
     */
    @ManyToOne
    @JoinColumn(name = "propertyId", nullable = false)
    private Property property;

    /**
     * Message or details provided by the user about the inquiry.
     */
    private String message;

    /**
     * Priority level of the inquiry (e.g., High, Medium, Low).
     */
    private String priority;

    /**
     * Current status of the inquiry (e.g., Pending, Resolved, In Progress).
     */
    private String status;

    /**
     * Date when the inquiry was made.
     */
    private LocalDate inquiryDate;

    /**
     * Date when a response was provided by the admin.
     */
    private LocalDate responseDate;

    /**
     * Response or feedback given by the administrator regarding the inquiry.
     */
    private String adminResponse;

    /**
     * Contact details provided by the user for further communication.
     */
    private String contactDetails;
}
