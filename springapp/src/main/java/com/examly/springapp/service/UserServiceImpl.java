package com.examly.springapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
 
import com.examly.springapp.config.UserPrinciple;
import com.examly.springapp.dtos.UserLoginDTO;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
 
@Service


public class UserServiceImpl implements UserService, UserDetailsService {
 

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;


    // Constructor-based injection
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }


    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);  // Logger for logging events
    // Registers a new user by encrypting their password and saving them to the database
    public User registerUser(User user) {
    	logger.info("Attempting to create new registration with role: {}", user.getUserRole());
        user.setPassword(encoder.encode(user.getPassword()));
        user=userRepo.save(user);
        return user;
    }
   
 
    @Override
    // Loads a user from the database based on their email for authentication purposes
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User existingUser = userRepo.findByEmail(email);
        if (existingUser == null) {
            throw new UsernameNotFoundException("User name not found");
        }
        return UserPrinciple.build(existingUser);
    }
    
    // Authenticates a user by finding them in the database using email
    public User loginUser(UserLoginDTO user) {
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser == null) {
            throw new UserNotFoundException("User not found");
        }
        return existingUser;
    }


    public User getUserById(Long userId) {
       return userRepo.findById(userId).orElse(null);
    }
}
  