package com.examly.springapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private Logger logger = LoggerFactory.getLogger(PropertyInquiryServiceImpl.class);
@Autowired
PropertyInquiryRepo propertyInquiryRepo;
@Autowired
UserRepo userRepo;
@Autowired
PropertyRepo propertyRepo;
    public PropertyInquiry addInquiry(PropertyInquiry propertyInquiry) {
        logger.info("Adding new inquiry for property ID:");
       User user = userRepo.findById(propertyInquiry.getUser().getUserId()).orElse(null);
       Property property = propertyRepo.findById(propertyInquiry.getProperty().getPropertyId()).orElse(null);
       if(user==null){
        logger.warn("User not found for ID: {}", propertyInquiry.getUser().getUserId());
       throw new UserNotFoundException("User not found!");
       }
       if(property==null){
        logger.warn("Property not found for ID: {}", propertyInquiry.getProperty().getPropertyId());
       throw new PropertyException("Property not found!");
       }
       logger.info("Inquiry added successfully");
       return propertyInquiryRepo.save(propertyInquiry);
    }

    public PropertyInquiry getInquiryById(long inquiryId) {
        logger.info("Fetching inquiry with ID: {}", inquiryId);
       PropertyInquiry propertyInquiry =  propertyInquiryRepo.findById(inquiryId).orElse(null);
       if(propertyInquiry==null){
        logger.warn("Inquiry not found for ID:");
        throw new InquiryNotFound("Inquiry not found!");
       }
       logger.info("Inquiry retrieved successfully for ID: {}", inquiryId);

       return propertyInquiry;
    }

    public List<PropertyInquiry> getInquiriesByUser(long userId) {
        logger.info("Fetching inquiries for user ID:");
       User user = userRepo.findById(userId).orElse(null);
       if(user == null){
        logger.warn("User not found for ID: {}", userId);
        throw new UserNotFoundException("User not found!");
       }
       return propertyInquiryRepo.findInquiryByUser(userId);
    }

    public List<PropertyInquiry> getAllInquiries() {
        logger.info("Fetching all inquiries");
        return propertyInquiryRepo.findAll();
    }

    public PropertyInquiry updateInquiryById(long inquiryId, PropertyInquiry inquiries) {
        logger.info("Attempting to update inquiry");
        PropertyInquiry propertyInquiry = propertyInquiryRepo.findById(inquiryId).orElse(null);
        if(propertyInquiry==null){
            logger.warn("Inquiry not found for ID: {}", inquiryId);

            throw new InquiryNotFound("Inquiry not found!"); 
        }
        inquiries.setInquiryId(inquiryId);
        return propertyInquiryRepo.save(inquiries);
    }

    public boolean deleteInquiryById(long inquiryId) {
        logger.info("Attempting to delete inquiry ID:");
        PropertyInquiry propertyInquiry = propertyInquiryRepo.findById(inquiryId).orElse(null);
        if(propertyInquiry==null){
            logger.warn("Inquiry not found for ID: {}", inquiryId);

            return false;
        }
        propertyInquiryRepo.deleteById(inquiryId);
        logger.info("Inquiry deleted successfully");
        return true;
    }

}
