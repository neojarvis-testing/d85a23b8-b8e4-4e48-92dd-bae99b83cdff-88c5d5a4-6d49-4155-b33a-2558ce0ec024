package com.examly.springapp.dtos;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PropertyInquiryInput {

    //@Positive(message = "User ID must be a positive number") // Validate userId as positive
    private long userId;

    //@Positive(message = "Property ID must be a positive number") // Validate propertyId as positive
    private long propertyId;

    //@NotBlank(message = "Message cannot be blank") // Ensure message is not empty
    //@Size(max = 500, message = "Message must not exceed 500 characters") // Limit message length
    private String message;

    //@NotBlank(message = "Priority cannot be blank") // Ensure priority is not blank
    //@Pattern(regexp = "^(High|Medium|Low)$", message = "Priority must be 'High', 'Medium', or 'Low'") // Validate priority options
    private String priority;

    //@NotBlank(message = "Status cannot be blank") // Ensure status is not blank
    //@Pattern(regexp = "^(Open|InProgress|Closed)$", message = "Status must be 'Open', 'InProgress', or 'Closed'") // Validate status options
    private String status;

    //@PastOrPresent(message = "Inquiry date cannot be in the future") // Ensure inquiryDate is not in the future
    private LocalDate inquiryDate;

    //@FutureOrPresent(message = "Response date cannot be in the past") // Ensure responseDate is not in the past
    private LocalDate responseDate;

    //@Size(max = 800, message = "Admin response must not exceed 500 characters") // Limit adminResponse length
    private String adminResponse;

    //@NotBlank(message = "Contact details cannot be blank") // Ensure contactDetails is not blank
    //@Pattern(regexp = "^[0-9]{10}$", message = "Contact details must be a valid 10-digit phone number") // Validate contact details as phone number
    private String contactDetails;

    // Generate Getters and Setters
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
