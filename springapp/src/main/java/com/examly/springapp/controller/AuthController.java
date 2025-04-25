package com.examly.springapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class AuthController {
    
    @Autowired
    UserServiceImpl userService;

    

    @PostMapping("/register")
    public ResponseEntity<LoginDTO>registration(@RequestBody User user){
        user=userService.registration(user);
        LoginDTO loginDto=new LoginDTO(user.getUserId(),user.getEmail(),user.getUsername(),user.getUserRole());
        return ResponseEntity.status(201).body(loginDto);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user){
        user=userService.loginUser(user);
        user.setPassword(null);
        return ResponseEntity.status(201).body(user);           
    }
}
