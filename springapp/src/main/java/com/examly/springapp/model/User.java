package com.examly.springapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a User entity mapped to the database.
 * This class includes validation annotations to enforce constraints on user attributes.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * Unique identifier for each user.
     * The ID is auto-generated using the identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    /**
     * Email address of the user.
     * Must be unique and follow a valid email format.
     * Cannot be blank.
     */
    @Column(unique = true)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    /**
     * Password of the user.
     * Cannot be blank and must be at least 8 characters long for security purposes.
     */
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    /**
     * Username chosen by the user.
     * Cannot be blank.
     */
    @NotBlank(message = "Username is mandatory")
    private String username;

    /**
     * Mobile number of the user.
     * It can optionally be validated to ensure it consists of exactly 10 digits.
     */
    private String mobileNumber;

    /**
     * Role assigned to the user (e.g., Admin, Customer, etc.).
     * Cannot be blank.
     */
    @NotBlank(message = "User role is mandatory")
    private String userRole;
}
