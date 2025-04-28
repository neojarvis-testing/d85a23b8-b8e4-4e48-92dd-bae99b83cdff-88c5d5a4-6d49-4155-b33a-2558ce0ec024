package com.examly.springapp.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.examly.springapp.dtos.UserLoginDTO;
import com.examly.springapp.model.User;

 
public interface UserService {
public User registerUser(User userDTO);
public UserDetails loadUserByUsername(String email);
public User loginUser(UserLoginDTO loginDTO);
}