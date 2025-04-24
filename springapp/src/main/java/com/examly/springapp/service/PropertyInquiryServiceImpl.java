package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.PropertyInquiry;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.PropertyInquiryRepo;
import com.examly.springapp.repository.UserRepo;
@Service
public class PropertyInquiryServiceImpl {
@Autowired
PropertyInquiryRepo propertyInquiryRepo;
@Autowired
UserRepo userRepo;
    public PropertyInquiry addInquiry(PropertyInquiry propertyInquiry) {
       return propertyInquiryRepo.save(propertyInquiry);
    }

    public PropertyInquiry getInquiryById(long inquiryId) {
       return propertyInquiryRepo.findById(inquiryId).orElse(null);
    }

    public List<PropertyInquiry> getInquiriesByUser(long userId) {
       User user = userRepo.findById(userId).orElse(null);
       if(user == null){
        return null;
       }
       return propertyInquiryRepo.findInquiryByUser(userId);
    }

    public List<PropertyInquiry> getAllInquiries() {
        return propertyInquiryRepo.findAll();
    }

    public PropertyInquiry updateInquiryById(long inquiryId, PropertyInquiry inquiries) {
        PropertyInquiry propertyInquiry = propertyInquiryRepo.findById(inquiryId).orElse(null);
        if(propertyInquiry==null){
            return null;
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
