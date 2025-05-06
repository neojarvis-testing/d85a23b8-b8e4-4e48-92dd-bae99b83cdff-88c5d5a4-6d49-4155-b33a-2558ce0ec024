package com.examly.springapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.examly.springapp.dtos.PropertyInquiryInput;
import com.examly.springapp.exceptions.InquiryNotFound;
import com.examly.springapp.exceptions.PropertyException;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.model.Property;
import com.examly.springapp.model.PropertyInquiry;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.PropertyInquiryRepo;
import com.examly.springapp.repository.PropertyRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class PropertyInquiryServiceImpl implements PropertyInquiryService {

    // Logger for logging various events in the service
    private Logger logger = LoggerFactory.getLogger(PropertyInquiryServiceImpl.class);

    // Repository dependencies for handling database operations
    private final PropertyInquiryRepo propertyInquiryRepo;
    private final UserRepo userRepo;
    private final PropertyRepo propertyRepo;

    /**
     * Constructor-based dependency injection for repositories.
     * @param propertyInquiryRepo Repository for Property Inquiries
     * @param userRepo Repository for Users
     * @param propertyRepo Repository for Properties
     */
    public PropertyInquiryServiceImpl(PropertyInquiryRepo propertyInquiryRepo, UserRepo userRepo, PropertyRepo propertyRepo) {
        this.propertyInquiryRepo = propertyInquiryRepo;
        this.userRepo = userRepo;
        this.propertyRepo = propertyRepo;
    }

    /**
     * Adds a new property inquiry after validating user and property existence.
     * @param propertyInquiry Input data for the property inquiry
     * @return The saved PropertyInquiry object
     * @throws UserNotFoundException if the user does not exist
     * @throws PropertyException if the property does not exist
     */
    public PropertyInquiry addInquiry(PropertyInquiryInput propertyInquiry) {
        // Retrieve user by ID
        User user = userRepo.findById(propertyInquiry.getUserId()).orElse(null);
        Property property = propertyRepo.findById(propertyInquiry.getPropertyId()).orElse(null);

        // Validate if user exists, else throw an exception
        if (user == null) {
            logger.warn("User not found for ID: {}", propertyInquiry.getUserId());
            throw new UserNotFoundException("User not found!");
        }

        // Validate if property exists, else throw an exception
        if (property == null) {
            logger.warn("Property not found for ID: {}", propertyInquiry.getPropertyId());
            throw new PropertyException("Property not found!");
        }

        logger.info("Inquiry added successfully");

        // Create new property inquiry and set properties
        PropertyInquiry newPropertyInquiry = new PropertyInquiry();
        newPropertyInquiry.setUser(user);
        newPropertyInquiry.setProperty(property);
        newPropertyInquiry.setAdminResponse(propertyInquiry.getAdminResponse());
        newPropertyInquiry.setContactDetails(propertyInquiry.getContactDetails());
        newPropertyInquiry.setInquiryDate(propertyInquiry.getInquiryDate());
        newPropertyInquiry.setResponseDate(propertyInquiry.getResponseDate());
        newPropertyInquiry.setMessage(propertyInquiry.getMessage());
        newPropertyInquiry.setPriority(propertyInquiry.getPriority());
        newPropertyInquiry.setStatus(propertyInquiry.getStatus());

        logger.info("Inquiry added successfully");
        return propertyInquiryRepo.save(newPropertyInquiry);
    }

    /**
     * Retrieves a property inquiry by ID.
     * @param inquiryId ID of the inquiry to fetch
     * @return The found PropertyInquiry object
     * @throws InquiryNotFound if the inquiry does not exist
     */
    public PropertyInquiry getInquiryById(long inquiryId) {
        logger.info("Fetching inquiry with ID: {}", inquiryId);
        PropertyInquiry propertyInquiry = propertyInquiryRepo.findById(inquiryId).orElse(null);

        // Validate existence, else throw an exception
        if (propertyInquiry == null) {
            logger.warn("Inquiry not found for ID: {}", inquiryId);
            throw new InquiryNotFound("Inquiry not found!");
        }

        logger.info("Inquiry retrieved successfully for ID: {}", inquiryId);
        return propertyInquiry;
    }

    /**
     * Retrieves all property inquiries associated with a user.
     * @param userId ID of the user
     * @return List of PropertyInquiry objects
     * @throws UserNotFoundException if the user does not exist
     */
    public List<PropertyInquiry> getInquiriesByUser(long userId) {
        logger.info("Fetching inquiries for user ID: {}", userId);
        User user = userRepo.findById(userId).orElse(null);

        if (user == null) {
            logger.warn("User not found for ID: {}", userId);
            throw new UserNotFoundException("User not found!");
        }

        return propertyInquiryRepo.findInquiryByUser(userId);
    }

    /**
     * Retrieves all property inquiries from the database.
     * @return List of all PropertyInquiry objects
     */
    public List<PropertyInquiry> getAllInquiries() {
        logger.info("Fetching all inquiries");
        return propertyInquiryRepo.findAll();
    }

    /**
     * Updates an existing property inquiry by ID.
     * @param inquiryId ID of the inquiry to update
     * @param inquiries Updated PropertyInquiry object
     * @return The updated inquiry object
     * @throws InquiryNotFound if the inquiry does not exist
     */
    public PropertyInquiry updateInquiryById(long inquiryId, PropertyInquiry inquiries) {
        logger.info("Attempting to update inquiry with ID: {}", inquiryId);
        PropertyInquiry propertyInquiry = propertyInquiryRepo.findById(inquiryId).orElse(null);

        if (propertyInquiry == null) {
            logger.warn("Inquiry not found for ID: {}", inquiryId);
            throw new InquiryNotFound("Inquiry not found!");
        }

        User user = userRepo.findById(inquiries.getUser().getUserId()).orElse(null);
        Property property = propertyRepo.findById(inquiries.getProperty().getPropertyId()).orElse(null);

        inquiries.setInquiryId(inquiryId);
        inquiries.setUser(user);
        inquiries.setProperty(property);

        return propertyInquiryRepo.save(inquiries);
    }

    /**
     * Deletes a property inquiry using the inquiry ID. 
     * @param inquiryId ID of the inquiry to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteInquiryById(long inquiryId) {
        logger.info("Attempting to delete inquiry with ID: {}", inquiryId);
        PropertyInquiry propertyInquiry = propertyInquiryRepo.findById(inquiryId).orElse(null);

        // Validate existence before deletion
        if (propertyInquiry == null) {
            logger.warn("Inquiry not found for ID: {}", inquiryId);
            return false;
        }

        propertyInquiryRepo.deleteById(inquiryId);
        logger.info("Inquiry deleted successfully");
        return true;
    }
}
