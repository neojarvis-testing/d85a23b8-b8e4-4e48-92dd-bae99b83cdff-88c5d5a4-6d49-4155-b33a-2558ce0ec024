package com.examly.springapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
 
 
public class LoginDTO {
    @NotBlank(message = "Token cannot be empty")
    private String token;
    @NotNull(message = "User ID cannot be null")
    // User ID must not be null to ensure valid identification

    private long userId;

    private String username;
    
    private String userRole;
 
    // Constructor 
    public LoginDTO(String token, long userId, String username, String userRole) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.userRole = userRole;
    }
   
 
    public LoginDTO() {
    }
 
 
    // Getters and setters
    public String getToken() {
        return token;
    }
 
    public void setToken(String token) {
        this.token = token;
    }
 
    public long getUserId() {
        return userId;
    }
 
    public void setUserId(long userId) {
        this.userId = userId;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getUserRole() {
        return userRole;
    }
 
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
