package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Property;

public interface PropertyRepo extends JpaRepository<Property,Long>{
    
}
