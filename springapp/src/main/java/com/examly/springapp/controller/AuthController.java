package com.examly.springapp.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.dtos.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;
 
import jakarta.validation.Valid;
 
@RestController
public class AuthController {
 
    private UserServiceImpl userService;
 
    @Autowired
    public AuthController(UserServiceImpl uServiceImpl){
        this.userService=uServiceImpl;
    }
 
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
 
   
    @PostMapping("/api/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user){
        return ResponseEntity.status(201).body(userService.registerUser(user));
    }
 
    @PostMapping("/api/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);
        User existingUser = userService.loginUser(user);
        System.out.print("User Role "+existingUser.getUserRole());
        System.out.println("User Id "+existingUser.getUserId());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        LoginDTO response = new LoginDTO(token, existingUser.getUserId(), existingUser.getUsername(), existingUser.getUserRole());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
 
}