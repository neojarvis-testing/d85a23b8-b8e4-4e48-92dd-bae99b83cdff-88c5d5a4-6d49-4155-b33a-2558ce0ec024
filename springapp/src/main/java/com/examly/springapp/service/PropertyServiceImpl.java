package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Property;
import com.examly.springapp.repository.PropertyRepo;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired 
    private PropertyRepo propertyRepo; // Repository to handle database operations

    /**
     * Adds a new property to the system.
     * @param property The property object to be added.
     * @return The saved property object.
     */
    public Property addProperty(Property property) {
        return propertyRepo.save(property); // Save the property object to the database
    }

    /**
     * Retrieves a property by its ID.
     * @param propertyId The ID of the property.
     * @return An Optional containing the property if found, otherwise empty.
     */
    @Override
    public Optional<Property> getPropertyById(Long propertyId) {
        return propertyRepo.findById(propertyId); // Fetch the property object by ID
    }

    /**
     * Retrieves a list of all properties.
     * @return A list containing all property objects.
     */
    @Override
    public List<Property> getAllProperties() {
        return propertyRepo.findAll(); // Fetch all property objects
    }

    /**
     * Updates an existing property in the system.
     * @param propertyId The ID of the property to update.
     * @param property The updated property details.
     * @return The updated property object, or null if the property does not exist.
     */
    @Override
    public Property updateProperty(Long propertyId, Property property) {
        // Check if the property exists
        Property exists = propertyRepo.findById(propertyId).orElse(null);
        if (exists == null) {
            return null; // Return null if the property does not exist
        }

        // Update the fields of the existing property with new values
        exists.setTitle(property.getTitle());
        exists.setDescription(property.getDescription());
        exists.setLocation(property.getLocation());
        exists.setPrice(property.getPrice());
        exists.setType(property.getType());
        exists.setStatus(property.getStatus());
        
        // Save the updated property to the database
        return propertyRepo.save(exists);
    }

    /**
     * Deletes a property by its ID.
     * @param propertyId The ID of the property to delete.
     * @return true if the property was successfully deleted, false otherwise.
     */
    public boolean deleteProperty(Long propertyId) {
        // Check if the property exists and delete it if found
        return propertyRepo.findById(propertyId).map(property -> {
            propertyRepo.delete(property); // Delete the property
            return true; // Return true if deletion succeeds
        }).orElse(false); // Return false if property does not exist
    }

}