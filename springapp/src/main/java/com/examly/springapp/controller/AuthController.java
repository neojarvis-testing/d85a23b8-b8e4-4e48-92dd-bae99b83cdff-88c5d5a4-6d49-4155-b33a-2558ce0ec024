package com.examly.springapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.DTOs.LoginDTO;
import com.examly.springapp.DTOs.UserDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;

@RestController //Defines this class as a REST controller

@RequestMapping("/api") // Sets base URL

public class AuthController {
    
    @Autowired
    UserServiceImpl userService;   //Injecting the service

    @PostMapping("/register")
    public ResponseEntity<?>registration(@RequestBody User user){
        user=userService.registration(user);
        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")  //Handles POST requests for user login
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        UserDTO userDTO=userService.loginUser(loginDTO);
        return ResponseEntity.status(201).body(userDTO);           
    }
}
