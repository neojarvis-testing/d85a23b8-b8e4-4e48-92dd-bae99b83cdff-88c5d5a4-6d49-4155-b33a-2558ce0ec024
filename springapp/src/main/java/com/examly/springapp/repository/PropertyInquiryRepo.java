package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.PropertyInquiry;

@Repository
public interface PropertyInquiryRepo extends JpaRepository<PropertyInquiry,Long>{
    
}
