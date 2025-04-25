package com.examly.springapp.service;
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

    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder encoder;

    public User registration(User user) {

        if (userRepo.findByEmail(user.getEmail()) != null)

            throw new UserAlreadyExistException("User with Same email:" + user.getEmail() + " already exists!!");

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public UserDTO loginUser(LoginDTO loginDTO) {
        User userexist = userRepo.findByEmail(loginDTO.getEmail());
        if (userexist == null) {
            
            System.out.println("User not found");
        }

        if (!encoder.matches(loginDTO.getPassword(), userexist.getPassword()))
            throw new PasswordNotMatchedException("Password is Incorrect!");

        return RowMapper.mapToUserDTO(userexist);
    }

}
