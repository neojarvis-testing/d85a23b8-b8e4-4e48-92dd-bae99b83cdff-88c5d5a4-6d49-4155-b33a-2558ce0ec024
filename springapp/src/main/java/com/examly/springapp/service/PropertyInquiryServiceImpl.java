package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class PropertyInquiryServiceImpl implements PropertyInquiryService{
@Autowired
PropertyInquiryRepo propertyInquiryRepo;
@Autowired
UserRepo userRepo;
@Autowired
PropertyRepo propertyRepo;
    public PropertyInquiry addInquiry(PropertyInquiryInput propertyInquiry) {
       User user = userRepo.findById(propertyInquiry.getUserId()).orElse(null);
       Property property = propertyRepo.findById(propertyInquiry.getPropertyId()).orElse(null);
       if(user==null){
       throw new UserNotFoundException("User not found!");
       }
       if(property==null){
       throw new PropertyException("Property not found!");
       }
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
       return propertyInquiryRepo.save(newPropertyInquiry);
    }

    public PropertyInquiry getInquiryById(long inquiryId) {
       PropertyInquiry propertyInquiry =  propertyInquiryRepo.findById(inquiryId).orElse(null);
       if(propertyInquiry==null){
        throw new InquiryNotFound("Inquiry not found!");
       }
       return propertyInquiry;
    }

    public List<PropertyInquiry> getInquiriesByUser(long userId) {
       User user = userRepo.findById(userId).orElse(null);
       if(user == null){
        throw new UserNotFoundException("User not found!");
       }
       return propertyInquiryRepo.findInquiryByUser(userId);
    }

    public List<PropertyInquiry> getAllInquiries() {
        return propertyInquiryRepo.findAll();
    }

    public PropertyInquiry updateInquiryById(long inquiryId, PropertyInquiry inquiries) {
        PropertyInquiry propertyInquiry = propertyInquiryRepo.findById(inquiryId).orElse(null);
        if(propertyInquiry==null){
            throw new InquiryNotFound("Inquiry not found!"); 
        }
        inquiries.setInquiryId(inquiryId);
        return propertyInquiryRepo.save(inquiries);
    }

    public boolean deleteInquiryById(long inquiryId) {
        PropertyInquiry propertyInquiry = propertyInquiryRepo.findById(inquiryId).orElse(null);
        if(propertyInquiry==null){
            return false;
        }
        propertyInquiryRepo.deleteById(inquiryId);
        return true;
    }

}
