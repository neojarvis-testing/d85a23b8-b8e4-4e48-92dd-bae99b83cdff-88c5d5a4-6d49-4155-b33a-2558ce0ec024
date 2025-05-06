package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.dtos.LoginDTO;
import com.examly.springapp.dtos.UserLoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST Controller responsible for authentication and user-related operations.
 * Provides endpoints for user registration, login, and retrieving user details.
 */
@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    /**
     * Constructor-based dependency injection for authentication and user services.
     * @param userServiceImpl Service for handling user operations
     * @param authenticationManager Authentication manager for validating user credentials
     */
    @Autowired
    public AuthController(UserServiceImpl userServiceImpl, AuthenticationManager authenticationManager) {
        this.userService = userServiceImpl;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = new JwtUtils();
    }

    /**
     * Registers a new user in the system.
     * @param user The user object containing registration details
     * @return ResponseEntity containing the registered user and HTTP status
     */
    @PostMapping("/api/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        logger.info("Registering new user: {}", user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));
    }

    /**
     * Authenticates a user and generates a JWT token for authorization.
     * @param loginDTO Data transfer object containing login credentials
     * @return ResponseEntity containing authentication token and user details or error message
     */
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

    /**
     * Retrieves user details by ID.
     * @param userId ID of the user to retrieve
     * @return ResponseEntity containing user details or error message
     */
    @GetMapping("/api/user/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
        logger.info("Fetching user details for ID: {}", userId);

        User user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
