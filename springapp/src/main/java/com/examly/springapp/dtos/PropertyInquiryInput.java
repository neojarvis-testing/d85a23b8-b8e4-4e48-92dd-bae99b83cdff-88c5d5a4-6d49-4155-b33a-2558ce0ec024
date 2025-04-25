package com.examly.springapp.dtos;

import java.time.LocalDate;

public class PropertyInquiryInput {
private long userId;
private long propertyId;
private String message;
private String priority;
private String status;
private LocalDate inquiryDate;
private LocalDate responseDate;
private String adminResponse;
private String contactDetails;
public long getUserId() {
    return userId;
}
public void setUserId(long userId) {
    this.userId = userId;
}
public long getPropertyId() {
    return propertyId;
}
public void setPropertyId(long propertyId) {
    this.propertyId = propertyId;
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
