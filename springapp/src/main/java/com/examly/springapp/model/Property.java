package com.examly.springapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a property entity mapped to the database.
 * Contains essential property attributes including title, description, and price.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    /**
     * Unique identifier for each property.
     * The ID is auto-generated using the identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long propertyId;

    /**
     * Title or name of the property.
     */
    private String title;

    /**
     * Description providing details about the property.
     */
    private String description;

    /**
     * Location where the property is situated.
     */
    private String location;

    /**
     * Price of the property.
     */
    private Double price;

    /**
     * Type of the property (e.g., Apartment, House, Commercial Space).
     */
    private String type;

    /**
     * Current status of the property (e.g., Available, Sold, Rented).
     */
    private String status;

    /**
     * Flag indicating whether the property has been deleted.
     * Default value is 0 (not deleted).
     */
    private int deleted = 0;

    /**
     * Provides a string representation of the property entity.
     * Displays the property ID, title, and deleted flag.
     * 
     * @return String representation of the property object
     */
    public String toString() {
        return this.propertyId + " " + this.title + " " + this.deleted;
    }
}
