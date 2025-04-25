package com.examly.springapp.DTOs;

import com.examly.springapp.model.User;

public class RowMapper {

  public static UserDTO mapToUserDTO(User user){
    UserDTO userDTO = new UserDTO();
    userDTO.setUserId(user.getUserId());
    userDTO.setUserRole(user.getUserRole());
    userDTO.setEmail(user.getEmail());
    return userDTO;
  }

}
