package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.model.Property;

public interface PropertyService {
    Property addProperty(Property property);

    Optional<Property> getPropertyById(Long propertyId);

    List<Property> getAllProperties();

    Property updateProperty(Long propertyId, Property property);

    boolean deleteProperty(Long propertyId);
   
}
