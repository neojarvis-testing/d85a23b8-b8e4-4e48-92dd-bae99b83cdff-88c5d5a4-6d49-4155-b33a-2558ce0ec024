package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.PropertyInquiry;

public interface PropertyInquiryRepo extends JpaRepository<PropertyInquiry,Long>{
    
}
