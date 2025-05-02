package com.examly.springapp.dtos;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {

    private long feedbackId;

    @NotBlank(message = "Feedback text cannot be blank") // Ensure feedback text is not blank
    @Size(max = 800, message = "Feedback text must not exceed 500 characters") // Limit the text length
    private String feedbackText;

    @PastOrPresent(message = "Date cannot be in the future") // Ensure date is not in the future
    private LocalDate date;

    // @Positive(message = "User ID must be a positive number") // Ensure userId is positive
    private Long userId;

    // @Positive(message = "Property ID must be a positive number") // Ensure propertyId is positive
    private Long propertyId;

    @NotBlank(message = "Category cannot be blank") // Ensure category is not blank
    private String category;

}
