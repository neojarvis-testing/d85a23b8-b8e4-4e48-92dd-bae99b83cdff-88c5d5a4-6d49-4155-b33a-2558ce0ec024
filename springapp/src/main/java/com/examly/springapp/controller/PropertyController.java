package com.examly.springapp.controller;

import java.util.List;


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
import com.examly.springapp.service.PropertyService;
import  com.examly.springapp.dtos.ApiResponse;
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyservice; // Dependency injected via constructor

    // Constructor-based injection
    public PropertyController(PropertyService propertyservice) {
        this.propertyservice = propertyservice;
    }



    // Add Property: Adds a new property to the system
    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        Property createdProperty = propertyservice.addProperty(property); // Call service to add property
      
            return ResponseEntity.status(201).body(createdProperty); // Return 201 Created if successful
    
    }

    // View Property by ID: Retrieves a property by its ID
    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getPropertyById(@PathVariable long propertyId) {

       
    Property property = propertyservice.getPropertyById(propertyId); // Call service to fetch property by ID

            return ResponseEntity.status(201).body(property); // Return 201 OK with property if found
  
    }

    // View All Properties: Retrieves a list of all properties
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> propertyList = propertyservice.getAllProperties(); // Call service to fetch all properties
            
            return ResponseEntity.status(200).body(propertyList);// Return 200 OK with list of properties
   
    }

    // Update Property: Updates an existing property by ID
    @PutMapping("/{propertyId}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long propertyId, @RequestBody Property property) {
        Property updatedProperty = propertyservice.updateProperty(propertyId, property); // Call service to update property
            return ResponseEntity.status(201).body(updatedProperty); // Return 201 OK with updated property if successful
    }

    // Delete Property: Deletes a property by its ID
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> deleteProperty(@PathVariable Long propertyId) {

        propertyservice.deleteProperty(propertyId);
        ApiResponse response = new ApiResponse("Deleted Successfully");

        return ResponseEntity.status(200).body(response); // Return 200 OK if deletion is successful
    }
}