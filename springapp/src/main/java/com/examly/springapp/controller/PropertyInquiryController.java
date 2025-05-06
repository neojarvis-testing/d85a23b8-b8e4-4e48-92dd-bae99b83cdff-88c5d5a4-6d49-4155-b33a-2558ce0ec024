package com.examly.springapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.dtos.ApiResponse;
import com.examly.springapp.dtos.PropertyInquiryInput;
import com.examly.springapp.model.PropertyInquiry;
import com.examly.springapp.service.PropertyInquiryServiceImpl;

/**
 * REST Controller for managing property inquiries.
 * Handles CRUD operations for inquiries, including retrieval, creation, update, and deletion.
 */
@RestController
@RequestMapping("/api/inquiries")
public class PropertyInquiryController {

    private final PropertyInquiryServiceImpl propertyInquiryService;

    /**
     * Constructor-based dependency injection for the property inquiry service.
     * @param propertyInquiryService Service for handling property inquiries
     */
    public PropertyInquiryController(PropertyInquiryServiceImpl propertyInquiryService) {
        this.propertyInquiryService = propertyInquiryService;
    }

    /**
     * Creates a new property inquiry.
     * @param propertyInquiryInput Input data for the inquiry
     * @return ResponseEntity containing the created inquiry and HTTP status
     */
    @PostMapping
    public ResponseEntity<PropertyInquiry> addInquiry(@RequestBody PropertyInquiryInput propertyInquiryInput) {
        PropertyInquiry propertyInquiry = propertyInquiryService.addInquiry(propertyInquiryInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyInquiry);
    }

    /**
     * Retrieves a specific inquiry by its ID.
     * @param inquiryId ID of the inquiry
     * @return ResponseEntity containing the inquiry details or error message
     */
    @GetMapping("/{inquiryId}")
    public ResponseEntity<Object> getInquiryById(@PathVariable long inquiryId) {
        PropertyInquiry propertyInquiry = propertyInquiryService.getInquiryById(inquiryId);
        if (propertyInquiry != null) {
            return ResponseEntity.status(HttpStatus.OK).body(propertyInquiry);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inquiry not found!");
    }

    /**
     * Retrieves all inquiries for a specific user.
     * @param userId ID of the user
     * @return ResponseEntity containing a list of inquiries or error message
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getInquiriesByUser(@PathVariable long userId) {
        List<PropertyInquiry> propertyInquiriesList = propertyInquiryService.getInquiriesByUser(userId);
        if (!propertyInquiriesList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(propertyInquiriesList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No inquiries found!");
    }

    /**
     * Retrieves all inquiries from the database.
     * @return ResponseEntity containing a list of inquiries or error message
     */
    @GetMapping
    public ResponseEntity<Object> getAllInquiries() {
        List<PropertyInquiry> propertyInquiriesList = propertyInquiryService.getAllInquiries();
        if (propertyInquiriesList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(propertyInquiriesList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No inquiries found!");
    }

    /**
     * Updates an existing inquiry by its ID.
     * @param inquiryId ID of the inquiry to update
     * @param inquiries Updated inquiry object
     * @return ResponseEntity containing updated inquiry or error message
     */
    @PutMapping("/{inquiryId}")
    public ResponseEntity<Object> updateInquiryById(@PathVariable long inquiryId, @RequestBody PropertyInquiry inquiries) {
        PropertyInquiry propertyInquiry = propertyInquiryService.updateInquiryById(inquiryId, inquiries);
        if (propertyInquiry != null) {
            return ResponseEntity.status(HttpStatus.OK).body(propertyInquiry);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Update unsuccessful!");
    }

    /**
     * Deletes an inquiry by its ID. 
     * @param inquiryId ID of the inquiry to delete
     * @return ResponseEntity containing success or failure message
     */
    @DeleteMapping("/{inquiryId}")
    public ResponseEntity<ApiResponse> deleteInquiryById(@PathVariable long inquiryId) {
        boolean flag = propertyInquiryService.deleteInquiryById(inquiryId);
        if (flag) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deletion successful!"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Deletion unsuccessful!"));
    }
}
