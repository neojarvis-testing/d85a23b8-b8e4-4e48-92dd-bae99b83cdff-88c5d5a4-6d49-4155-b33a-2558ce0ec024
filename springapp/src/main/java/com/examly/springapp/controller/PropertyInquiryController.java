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

import com.examly.springapp.model.PropertyInquiry;
import com.examly.springapp.service.PropertyInquiryServiceImpl;


@RestController
@RequestMapping("/api/inquiries")
public class PropertyInquiryController {
@Autowired
PropertyInquiryServiceImpl  propertyInquiryService;
@PostMapping
public ResponseEntity<PropertyInquiry> addInquiry(@RequestBody PropertyInquiry propertyInquiry){
    propertyInquiry = propertyInquiryService.addInquiry(propertyInquiry);
    return ResponseEntity.status(201).body(propertyInquiry);
}

@GetMapping("/{inquiryId}")
public ResponseEntity<?> getInquiryById(@PathVariable long inquiryId){
    PropertyInquiry propertyInquiry = propertyInquiryService.getInquiryById(inquiryId);
    if(propertyInquiry==null){
    return ResponseEntity.status(200).body(propertyInquiry);
    }
    return ResponseEntity.status(404).body("Inquiry not found!");
}

@GetMapping("/user/{userId}")
public ResponseEntity<?> getInquiriesByUser(@PathVariable long userId){
    List<PropertyInquiry> propertyInquiriesList = propertyInquiryService.getInquiriesByUser(userId);
    if(!propertyInquiriesList.isEmpty()){
    return ResponseEntity.status(200).body(propertyInquiriesList);
    }
    return ResponseEntity.status(404).body("No Inquiries found!");
}

@GetMapping("")
public ResponseEntity<?> getAllInquiries(){
    List<PropertyInquiry> propertyInquiriesList = propertyInquiryService.getAllInquiries();
    if(!propertyInquiriesList.isEmpty()){
    return ResponseEntity.status(200).body(propertyInquiriesList);
    }
    return ResponseEntity.status(404).body("No Inquiries found!");
}


@PutMapping("/{inquiryId}")
public ResponseEntity<?> updateInquiryById(@PathVariable long inquiryId,@RequestBody PropertyInquiry inquiries){
    PropertyInquiry propertyInquiry = propertyInquiryService.updateInquiryById(inquiryId,inquiries);
    if(propertyInquiry==null){
        return ResponseEntity.status(200).body(propertyInquiry);
    }
        return ResponseEntity.status(404).body("Update is Unsuccessful!");
}

@DeleteMapping("/{inquiryId}")
public ResponseEntity<String> deleteInquiryById(@PathVariable long inquiryId){
    boolean flag = propertyInquiryService.deleteInquiryById(inquiryId);
    if(flag){
        return ResponseEntity.status(200).body("Deletion is successfull!");
    }
    return ResponseEntity.status(404).body("Deletion is unsuccessfull!");
}



}
