package com.examly.springapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotBlank(message = "Token cannot be empty")
    private String token;
    @NotNull(message = "User ID cannot be null")
    // User ID must not be null to ensure valid identification

    private long userId;

    private String username;
    
    private String userRole;
}


 
    