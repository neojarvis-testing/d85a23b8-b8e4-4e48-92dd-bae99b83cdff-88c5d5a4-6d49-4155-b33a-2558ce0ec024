package com.examly.springapp.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity  // Maps the class with database
@Data
@NoArgsConstructor
@AllArgsConstructor
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

   
}
