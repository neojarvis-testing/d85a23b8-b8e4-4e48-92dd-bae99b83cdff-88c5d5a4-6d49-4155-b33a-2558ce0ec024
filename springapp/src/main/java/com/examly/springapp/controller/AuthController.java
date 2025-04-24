package com.examly.springapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;

@RestController //Defines this class as a REST controller

@RequestMapping("/api") // Sets base URL

public class AuthController {
    
    @Autowired
    UserServiceImpl userService;   //Injecting the service

    

    @PostMapping("/register") // Handles POST requests for user registration
    public ResponseEntity<User>registration(@RequestBody User user){
        user=userService.registration(user); // Calls registration method
        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")  //Handles POST requests for user login
    public ResponseEntity<User> loginUser(@RequestBody User user){
        user=userService.loginUser(user);
        user.setPassword(null);  // Encrypting the password
        return ResponseEntity.status(201).body(user);           
    }
}
