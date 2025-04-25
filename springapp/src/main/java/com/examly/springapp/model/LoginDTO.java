package com.examly.springapp.model;

public class LoginDTO {

    private long userId;
    private String email;
    private String username;
    private String UserRole;

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserRole() {
        return UserRole;
    }
    public void setUserRole(String userRole) {
        UserRole = userRole;
    }
    public LoginDTO(long userId, String email, String username, String userRole) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        UserRole = userRole;
    }
    
}
