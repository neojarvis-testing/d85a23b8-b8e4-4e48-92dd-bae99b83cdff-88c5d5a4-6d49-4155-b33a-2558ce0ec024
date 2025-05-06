package com.examly.springapp.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.examly.springapp.exceptions.DuplicatePropertyException;
import com.examly.springapp.exceptions.PropertyException;
import com.examly.springapp.model.Property;
import com.examly.springapp.repository.PropertyRepo;

@Service
public class PropertyServiceImpl implements PropertyService {
    private Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);

    private final PropertyRepo propertyRepo; // Repository to handle database operations

    // Constructor-based injection
    public PropertyServiceImpl(PropertyRepo propertyRepo) {
        this.propertyRepo = propertyRepo;
    }


    /**
     * Adds a new property to the system.
     * @param property The property object to be added.
     * @return The saved property object.
     * @throws DuplicatePropertyException If a property with the same title already exists.
     */
    public Property addProperty(Property property) {
        logger.info("Attempting to add property: {}", property.getTitle());
        // Check for duplicate property title
        Property existingProperty = propertyRepo.findByTitle(property.getTitle());
        if (existingProperty != null) {
            logger.warn("Duplicate property detected: {}", property.getTitle());
            throw new DuplicatePropertyException("Property with the title '" + property.getTitle() + "' already exists.");
        }
        // Save and return the new property object
        property.setDeleted(0);
        return propertyRepo.save(property);
    }

    /**
     * Retrieves a property by its ID.
     * @param propertyId The ID of the property.
     * @return The property object if found.
     * @throws PropertyException If the property is not found.
     */
    @Override
    public Property getPropertyById(Long propertyId) {
        logger.info("Fetching property with ID: {}", propertyId);

        // Fetch the property object by ID, or throw an exception if not found
        Property property= propertyRepo.findById(propertyId).orElse(null);
        if(property==null){
            logger.error("Property not found: ID {}", propertyId);
                throw new PropertyException("Property with ID " + propertyId + " not found.");
        }
        logger.info("Property retrieved successfully:");
        return property;

    }

    /**
     * Retrieves a list of all properties.
     * @return A list containing all property objects.
     */
    @Override
    public List<Property> getAllProperties() {
        logger.info("Fetching all properties");
        List<Property> list = propertyRepo.findAll();
        logger.info("List: {}",list);
        // Fetch and return all property objects
        return list;
    }

    /**
     * Updates an existing property in the system.
     * @param propertyId The ID of the property to update.
     * @param property The updated property details.
     * @return The updated property object.
     * @throws PropertyException If the property does not exist.
     */
    @Override
    public Property updateProperty(Long propertyId, Property property) {
        logger.info("Attempting to update property with ID: {}", propertyId);
        // Check if the property exists
        Property existingProperty= propertyRepo.findById(propertyId).orElse(null);
        if(existingProperty==null){
            logger.error("Property update failed, not found:");
            throw new PropertyException("Property with ID " + propertyId + " not found.");
    }

        // Update the fields of the existing property with new values
        existingProperty.setTitle(property.getTitle());
        existingProperty.setDescription(property.getDescription());
        existingProperty.setLocation(property.getLocation());
        existingProperty.setPrice(property.getPrice());
        existingProperty.setType(property.getType());
        existingProperty.setStatus(property.getStatus());

        // Save and return the updated property object
        logger.info("Property updated successfully:");
        return propertyRepo.save(existingProperty);
    }

    /**
     * Deletes a property by its ID.
     * @param propertyId The ID of the property to delete.
     * @return true if the property was successfully deleted.
     * @throws PropertyException If the property does not exist.
     */
    public boolean deleteProperty(Long propertyId) {
        logger.info("Attempting to delete property with ID: {}", propertyId);
        // Check if the property exists
        Property property= propertyRepo.findById(propertyId).orElse(null);
        if(property==null){
            logger.error("Property deletion failed, not found: ID {}", propertyId);
                throw new PropertyException("Property with ID " + propertyId + " not found.");
        }
        logger.info("Property found: {}", property);
      property.setDeleted(1);
      property = propertyRepo.save(property);
      logger.info("Property after marking as deleted: {}", property);
      logger.info("Property marked as deleted: ID {}", propertyId);
        // Perform a logical deletion by toggling the isDeleted status (if applicable)
       // Physically delete the property (or toggle isDeleted if needed)
       
        return true;
    }
}