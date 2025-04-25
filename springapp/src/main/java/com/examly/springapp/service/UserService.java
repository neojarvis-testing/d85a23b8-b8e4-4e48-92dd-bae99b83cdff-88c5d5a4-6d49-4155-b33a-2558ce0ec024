package com.examly.springapp.service;

import com.examly.springapp.dtos.LoginDTO;
import com.examly.springapp.dtos.UserDTO;
import com.examly.springapp.model.User;

public interface UserService {

public User registration(User user);

public UserDTO loginUser(LoginDTO user);



}
