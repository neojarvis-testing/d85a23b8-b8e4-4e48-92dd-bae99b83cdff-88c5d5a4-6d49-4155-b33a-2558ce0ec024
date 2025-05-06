package com.examly.springapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.model.Property;
import com.examly.springapp.service.PropertyService;
import com.examly.springapp.dtos.ApiResponse;

/**
 * REST Controller for managing property-related operations.
 * Handles CRUD operations including adding, retrieving, updating, and deleting properties.
 */
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    /**
     * Constructor-based dependency injection for the property service.
     * @param propertyService Service for handling property operations
     */
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * Adds a new property to the system.
     * @param property The property object to be added
     * @return ResponseEntity containing the created property and HTTP status
     */
    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        Property createdProperty = propertyService.addProperty(property);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
    }

    /**
     * Retrieves a property by its ID.
     * @param propertyId ID of the property to retrieve
     * @return ResponseEntity containing the property details or error message
     */
    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getPropertyById(@PathVariable long propertyId) {
        Property property = propertyService.getPropertyById(propertyId);
        return ResponseEntity.status(HttpStatus.OK).body(property);
    }

    /**
     * Retrieves all properties from the database.
     * @return ResponseEntity containing a list of properties or error message
     */
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> propertyList = propertyService.getAllProperties();
        return ResponseEntity.status(HttpStatus.OK).body(propertyList);
    }

    /**
     * Updates an existing property by its ID.
     * @param propertyId ID of the property to update
     * @param property Updated property object
     * @return ResponseEntity containing the updated property or error message
     */
    @PutMapping("/{propertyId}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long propertyId, @RequestBody Property property) {
        Property updatedProperty = propertyService.updateProperty(propertyId, property);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProperty);
    }

    /**
     * Deletes a property by its ID.
     * @param propertyId ID of the property to delete
     * @return ResponseEntity containing success message
     */
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> deleteProperty(@PathVariable Long propertyId) {
        propertyService.deleteProperty(propertyId);
        ApiResponse response = new ApiResponse("Deleted successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
