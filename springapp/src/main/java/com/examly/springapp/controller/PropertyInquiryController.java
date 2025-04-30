package com.examly.springapp.controller;

import java.util.List;

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

import com.examly.springapp.dtos.PropertyInquiryInput;
import com.examly.springapp.model.PropertyInquiry;
import com.examly.springapp.service.PropertyInquiryServiceImpl;

import jakarta.validation.Valid;


@RestController   //Defines this class as a REST controller
@RequestMapping("/api/inquiries")  //Set Base URL
public class PropertyInquiryController {
@Autowired 
PropertyInquiryServiceImpl  propertyInquiryService; //Injecting the service

@PostMapping    // Handles HTTP POST requests for adding a new property inquiry
public ResponseEntity<PropertyInquiry> addInquiry(@RequestBody PropertyInquiryInput propertyInquiryInput){
    PropertyInquiry propertyInquiry = propertyInquiryService.addInquiry(propertyInquiryInput);
    return ResponseEntity.status(201).body(propertyInquiry);
}

@GetMapping("/{inquiryId}")  // Handles HTTP GET requests for retrieving a specific inquiry by ID

public ResponseEntity<?> getInquiryById(@PathVariable long inquiryId){
    PropertyInquiry propertyInquiry = propertyInquiryService.getInquiryById(inquiryId);  // Fetches inquiry by ID

    if(propertyInquiry!=null){
    return ResponseEntity.status(200).body(propertyInquiry); //Returns success status if propertyInquiry not null
    }
    return ResponseEntity.status(404).body("Inquiry not found!"); //Returns 404 if not found
}

@GetMapping("/user/{userId}")   //Handles HTTP GET requests for retrieving all inquiries for a specific user
public ResponseEntity<?> getInquiriesByUser(@PathVariable long userId){
    List<PropertyInquiry> propertyInquiriesList = propertyInquiryService.getInquiriesByUser(userId);
    if(!propertyInquiriesList.isEmpty()){  // Returns list of inquiries if found
    return ResponseEntity.status(200).body(propertyInquiriesList);
    }
    return ResponseEntity.status(404).body("No Inquiries found!");  // Returns error response if no inquiries exist
}

@GetMapping
public ResponseEntity<?> getAllInquiries(){
    List<PropertyInquiry> propertyInquiriesList = propertyInquiryService.getAllInquiries();
    if(propertyInquiriesList!=null){
    return ResponseEntity.status(200).body(propertyInquiriesList);
    }
    return ResponseEntity.status(404).body("No Inquiries found!");
    
}


@PutMapping("/{inquiryId}")  // Handles HTTP PUT requests for updating a specific inquiry by ID
public ResponseEntity<?> updateInquiryById(@PathVariable long inquiryId,@RequestBody PropertyInquiry inquiries){
    PropertyInquiry propertyInquiry = propertyInquiryService.updateInquiryById(inquiryId,inquiries);
    if(propertyInquiry!=null){  // Returns updated inquiry if successful
        return ResponseEntity.status(200).body(propertyInquiry);
    }
    return ResponseEntity.status(404).body("Update is Unsuccessful!");
}

@DeleteMapping("/{inquiryId}")   //Handles HTTP DELETE requests for removing a specific inquiry by ID

public ResponseEntity<String> deleteInquiryById(@PathVariable long inquiryId){
    boolean flag = propertyInquiryService.deleteInquiryById(inquiryId);
    if(flag){
        return ResponseEntity.status(200).body("Deletion is successfull!");  // Confirms successful deletion
    }
    return ResponseEntity.status(404).body("Deletion is unsuccessfull!");  // Returns error response if deletion fails

}



}
