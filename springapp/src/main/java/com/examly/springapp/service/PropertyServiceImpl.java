package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.DuplicatePropertyException;
import com.examly.springapp.exceptions.PropertyException;
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
     * @throws DuplicatePropertyException If a property with the same title already exists.
     */
    public Property addProperty(Property property) {
        // Check for duplicate property title
        Property existingProperty = propertyRepo.findByTitle(property.getTitle());
        if (existingProperty != null) {
            throw new DuplicatePropertyException("Property with the title '" + property.getTitle() + "' already exists.");
        }
        // Save and return the new property object
        property.setIsdeleted(true);
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

        // Fetch the property object by ID, or throw an exception if not found
        Property property= propertyRepo.findById(propertyId).orElse(null);
        if(property==null){
                throw new PropertyException("Property with ID " + propertyId + " not found.");
        }
        return property;

    }

    /**
     * Retrieves a list of all properties.
     * @return A list containing all property objects.
     */
    @Override
    public List<Property> getAllProperties() {
        // Fetch and return all property objects
        return propertyRepo.findAll();
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
        // Check if the property exists
        Property existingProperty= propertyRepo.findById(propertyId).orElse(null);
        if(existingProperty==null){
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
        return propertyRepo.save(existingProperty);
    }

    /**
     * Deletes a property by its ID.
     * @param propertyId The ID of the property to delete.
     * @return true if the property was successfully deleted.
     * @throws PropertyException If the property does not exist.
     */
    public boolean deleteProperty(Long propertyId) {
        // Check if the property exists
        Property property= propertyRepo.findById(propertyId).orElse(null);
        if(property==null){
                throw new PropertyException("Property with ID " + propertyId + " not found.");
        }
      property.setIsdeleted(false);
      propertyRepo.save(property);
        // Perform a logical deletion by toggling the isDeleted status (if applicable)
       // Physically delete the property (or toggle isDeleted if needed)
        return true;
    }
}