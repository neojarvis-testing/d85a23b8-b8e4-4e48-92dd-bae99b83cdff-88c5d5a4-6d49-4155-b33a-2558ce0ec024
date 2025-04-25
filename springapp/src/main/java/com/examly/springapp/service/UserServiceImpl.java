package com.examly.springapp.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.DTOs.LoginDTO;
import com.examly.springapp.DTOs.RowMapper;
import com.examly.springapp.DTOs.UserDTO;
import com.examly.springapp.exceptions.PasswordNotMatchedException;
import com.examly.springapp.exceptions.UserAlreadyExistException;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder encoder;

    public User registration(User user) {
        logger.info("User registration started for email: {}", user.getEmail());
    

        if (userRepo.findByEmail(user.getEmail()) != null) {
            logger.warn("User with email {} already exists!", user.getEmail());

            throw new UserAlreadyExistException("User with Same email:" + user.getEmail() + " already exists!!");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public UserDTO loginUser(LoginDTO loginDTO) {
        logger.info("Method login started for email: {}", loginDTO.getEmail());
        User userexist = userRepo.findByEmail(loginDTO.getEmail());
        if (userexist == null) {
            
            System.out.println("User not found");
        }

        if (!encoder.matches(loginDTO.getPassword(), userexist.getPassword())) {
            logger.error("Password mismatch for email: {}", loginDTO.getEmail());
            throw new PasswordNotMatchedException("Password is Incorrect!");
        }
        logger.info("User login successful for email: {}", loginDTO.getEmail());
        return RowMapper.mapToUserDTO(userexist);
    }

}
