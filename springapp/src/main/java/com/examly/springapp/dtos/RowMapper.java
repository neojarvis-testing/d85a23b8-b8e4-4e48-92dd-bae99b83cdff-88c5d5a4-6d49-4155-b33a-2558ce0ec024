package com.examly.springapp.dtos;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.Property;
import com.examly.springapp.model.PropertyInquiry;
import com.examly.springapp.model.User;

public class RowMapper {

  public static UserDTO mapToUserDTO(User user){
    UserDTO userDTO = new UserDTO();
    userDTO.setUserId(user.getUserId());
    userDTO.setUserRole(user.getUserRole());
    userDTO.setEmail(user.getEmail());
    return userDTO;
  }

   public static Feedback mapToFeedbackDTO(FeedbackDTO feedbackDTO) {
	  Feedback feedback=new Feedback();
	  feedback.setCategory(feedbackDTO.getCategory());
	    feedback.setDate(feedbackDTO.getDate()); // Assigns the date from DTO
	    feedback.setFeedbackId(feedbackDTO.getFeedbackId()); // Uses DTO's ID
	    feedback.setFeedbackText(feedbackDTO.getFeedbackText()); // Assigns DTO's feedback text
	    // Fixing User assignment
	    User user = new User();
	    user.setUserId(feedbackDTO.getUserId());
	    feedback.setUser(user);

	    // Fixing Property assignment
	    Property property = new Property();
	    property.setPropertyId(feedbackDTO.getPropertyId());
	    feedback.setProperty(property);
	  
	  return feedback;
  }

}
