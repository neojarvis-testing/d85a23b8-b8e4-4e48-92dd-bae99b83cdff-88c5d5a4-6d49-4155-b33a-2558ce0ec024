package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Property;
import com.examly.springapp.service.PropertyServiceImpl;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired 
    private PropertyServiceImpl propertyservice; // Service layer to handle business logic

    // Add Property: Adds a new property to the system
    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        Property createdProperty = propertyservice.addProperty(property); // Call service to add property
        if (createdProperty != null) {
            return ResponseEntity.status(201).body(createdProperty); // Return 201 Created if successful
        } else {
            return ResponseEntity.status(409).body(null); // Return 409 Conflict if failure
        }
    }

    // View Property by ID: Retrieves a property by its ID
    @GetMapping("/{propertyId}")
    public ResponseEntity<Optional<Property>> getPropertyById(@PathVariable long propertyId) {
        Optional<Property> property = propertyservice.getPropertyById(propertyId); // Call service to fetch property by ID
        if (property != null) {
            return ResponseEntity.status(201).body(property); // Return 201 OK with property if found
        } else {
            return ResponseEntity.status(404).body(null); // Return 404 Not Found if no property exists with the ID
        }
    }

    // View All Properties: Retrieves a list of all properties
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> propertyList = propertyservice.getAllProperties(); // Call service to fetch all properties
        if (!propertyList.isEmpty()) {
            return ResponseEntity.status(201).body(propertyList); // Return 201 OK with list of properties
        } else {
            return ResponseEntity.status(404).body(null); // Return 404 Not Found if no properties exist
        }
    }

    // Update Property: Updates an existing property by ID
    @PutMapping("/{propertyId}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long propertyId, @RequestBody Property property) {
        Property updatedProperty = propertyservice.updateProperty(propertyId, property); // Call service to update property
        if (updatedProperty != null) {
            return ResponseEntity.status(201).body(updatedProperty); // Return 201 OK with updated property if successful
        } else {
            return ResponseEntity.status(404).body(null); // Return 404 Not Found if no property exists with the ID
        }
    }

    // Delete Property: Deletes a property by its ID
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long propertyId) {
        boolean isDeleted = propertyservice.deleteProperty(propertyId); // Call service to delete property
        if (isDeleted) {
            return ResponseEntity.status(200).body("Deleted Successfully"); // Return 200 OK if deletion is successful
        } else {
            return ResponseEntity.status(404).body("Property with Id not found!"); // Return 404 Not Found if no property exists with the ID
        }
    }
}