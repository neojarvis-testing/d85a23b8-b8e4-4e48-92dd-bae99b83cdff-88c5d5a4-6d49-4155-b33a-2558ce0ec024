package com.examly.springapp.dtos;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    
}
