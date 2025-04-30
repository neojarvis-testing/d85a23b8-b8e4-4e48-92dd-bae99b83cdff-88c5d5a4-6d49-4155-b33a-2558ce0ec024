package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.dtos.PropertyInquiryInput;
import com.examly.springapp.model.PropertyInquiry;

public interface PropertyInquiryService {

    public PropertyInquiry addInquiry(PropertyInquiryInput propertyInquiry);
    public PropertyInquiry getInquiryById(long inquiryId);
    public List<PropertyInquiry> getInquiriesByUser(long userId);
    public List<PropertyInquiry> getAllInquiries();
    public PropertyInquiry updateInquiryById(long inquiryId, PropertyInquiry inquiries);
    public boolean deleteInquiryById(long inquiryId);
}
