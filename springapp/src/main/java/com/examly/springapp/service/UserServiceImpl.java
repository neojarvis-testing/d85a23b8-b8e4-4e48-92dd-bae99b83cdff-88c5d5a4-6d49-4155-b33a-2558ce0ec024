package com.examly.springapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
 
import com.examly.springapp.config.UserPrinciple;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
 
@Service


public class UserServiceImpl implements UserService, UserDetailsService {
 
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
 
    @Autowired
    private PasswordEncoder encoder;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public User registerUser(User user) {
    	//logger.info("Attempting to create new feedback"+user.getUserRole());
        user.setPassword(encoder.encode(user.getPassword()));
        user=userRepo.save(user);
        return user;
    }
   
 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User existingUser = userRepo.findByEmail(email);
        if (existingUser == null) {
            throw new UsernameNotFoundException("User name not found");
        }
        return UserPrinciple.build(existingUser);
    }
 
    public User loginUser(User user) {
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser == null) {
            throw new UserNotFoundException("User not found");
        }
        // No need to encode the password again here
        return existingUser;
    }
}
  