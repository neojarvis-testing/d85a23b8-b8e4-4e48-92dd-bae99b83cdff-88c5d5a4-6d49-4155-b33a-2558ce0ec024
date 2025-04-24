package com.examly.springapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;

@RestController
public class AuthController {
    
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/api")

    @PostMapping("/register")
    public ResponseEntity<User>registration(@RequestBody User user){
        user=userService.registration(user);
        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user){
        user=userService.loginUser(user);
        user.setPassword(null);
        return ResponseEntity.status(201).body(user);           
    }
}
