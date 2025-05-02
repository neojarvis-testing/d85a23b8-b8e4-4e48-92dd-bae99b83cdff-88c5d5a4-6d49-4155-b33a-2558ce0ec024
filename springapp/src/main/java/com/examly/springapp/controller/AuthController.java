package com.examly.springapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.dtos.LoginDTO;
import com.examly.springapp.dtos.UserLoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(UserServiceImpl uServiceImpl, AuthenticationManager authenticationManager) {
        this.userService = uServiceImpl;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = new JwtUtils();
    }

   
    

    // Constructor-based injection
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/api/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        logger.info("Registering new user: {}", user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody UserLoginDTO loginDTO) {
        logger.info("Login request received for email: {}", loginDTO.getEmail());

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);

        User existingUser = userService.loginUser(loginDTO);
        
        if (existingUser == null) {
            logger.warn("Login failed for email: {}", loginDTO.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        logger.info("User logged in successfully: Role = {}, User ID = {}", existingUser.getUserRole(), existingUser.getUserId());

        LoginDTO response = new LoginDTO(token, existingUser.getUserId(), existingUser.getUsername(), existingUser.getUserRole());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/api/user/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
        logger.info("Fetching user details for ID: {}", userId);

        User user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}