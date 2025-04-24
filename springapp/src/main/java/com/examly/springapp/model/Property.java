package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Property {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private long propertyId;
private String title;
private String description;
private String location;
private Double price;
private String type;
private String status;
public long getPropertyId() {
    return propertyId;
}
public void setPropertyId(long propertyId) {
    this.propertyId = propertyId;
}
public String getTitle() {
    return title;
}
public void setTitle(String title) {
    this.title = title;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}
public String getLocation() {
    return location;
}
public void setLocation(String location) {
    this.location = location;
}
public Double getPrice() {
    return price;
}
public void setPrice(Double price) {
    this.price = price;
}
public String getType() {
    return type;
}
public void setType(String type) {
    this.type = type;
}
public String getStatus() {
    return status;
}
public void setStatus(String status) {
    this.status = status;
}
public Property(long propertyId, String title, String description, String location, Double price, String type,
        String status) {
    this.propertyId = propertyId;
    this.title = title;
    this.description = description;
    this.location = location;
    this.price = price;
    this.type = type;
    this.status = status;
}
public Property() {
}

}
