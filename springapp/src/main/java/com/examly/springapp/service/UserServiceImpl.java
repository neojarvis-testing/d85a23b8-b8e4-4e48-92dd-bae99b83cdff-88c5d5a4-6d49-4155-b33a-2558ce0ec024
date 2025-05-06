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

    /**
     * Constructor-based injection of dependencies.
     * Initializes the UserRepo and PasswordEncoder instances.
     * @param userRepo  Repository interface for User entity operations
     * @param encoder   Encoder to encrypt user passwords
     */
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    // Logger for logging various events in the service implementation
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * Registers a new user by encrypting their password and saving them to the database. 
     * @param user  The user to be registered
     * @return      The registered user with encrypted password
     */
    public User registerUser(User user) {
        logger.info("Attempting to create new registration with role: {}", user.getUserRole());
        user.setPassword(encoder.encode(user.getPassword()));
        user = userRepo.save(user);
        return user;
    }
   
    @Override
    /**
     * Loads a user from the database based on their email for authentication purposes.
     * @param email The email of the user
     * @return      UserDetails object if user exists
     * @throws UsernameNotFoundException if user does not exist
     */
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User existingUser = userRepo.findByEmail(email);
        if (existingUser == null) {
            throw new UsernameNotFoundException("User name not found");
        }
        return UserPrinciple.build(existingUser);
    }
    
    /**
     * Authenticates a user by finding them in the database using email.
     * @param user  Data Transfer Object containing login details
     * @return      The authenticated user if found
     * @throws UserNotFoundException if the user does not exist
     */
    public User loginUser(UserLoginDTO user) {
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser == null) {
            throw new UserNotFoundException("User not found");
        }
        return existingUser;
    }

    /**
     * Retrieves a user by their unique ID.
     * @param userId  The ID of the user
     * @return        The user if found, otherwise null
     */
    public User getUserById(Long userId) {
       return userRepo.findById(userId).orElse(null);
    }
}
