package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PropertyInquiry {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private long inquiryId;
@ManyToOne
@JoinColumn(name = "userId", nullable=false)
private User user;
@ManyToOne
@JoinColumn(name = "propertyId", nullable=false)
private Property property;
private String message;
private String priority;
private String status;
private LocalDate inquiryDate;
private LocalDate responseDate;
private String adminResponse;
private String contactDetails;
public long getInquiryId() {
    return inquiryId;
}
public void setInquiryId(long inquiryId) {
    this.inquiryId = inquiryId;
}
public User getUser() {
    return user;
}
public void setUser(User user) {
    this.user = user;
}
public Property getProperty() {
    return property;
}
public void setProperty(Property property) {
    this.property = property;
}
public String getMessage() {
    return message;
}
public void setMessage(String message) {
    this.message = message;
}
public String getPriority() {
    return priority;
}
public void setPriority(String priority) {
    this.priority = priority;
}
public String getStatus() {
    return status;
}
public void setStatus(String status) {
    this.status = status;
}
public LocalDate getInquiryDate() {
    return inquiryDate;
}
public void setInquiryDate(LocalDate inquiryDate) {
    this.inquiryDate = inquiryDate;
}
public LocalDate getResponseDate() {
    return responseDate;
}
public void setResponseDate(LocalDate responseDate) {
    this.responseDate = responseDate;
}
public String getAdminResponse() {
    return adminResponse;
}
public void setAdminResponse(String adminResponse) {
    this.adminResponse = adminResponse;
}
public String getContactDetails() {
    return contactDetails;
}
public void setContactDetails(String contactDetails) {
    this.contactDetails = contactDetails;
}




}
