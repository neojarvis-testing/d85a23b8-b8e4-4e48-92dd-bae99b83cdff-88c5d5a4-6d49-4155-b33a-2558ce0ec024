package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.dtos.FeedbackDTO;
import com.examly.springapp.model.Feedback;

public interface FeedbackService {

public Feedback createFeedback(FeedbackDTO feedbackDTO);
public Feedback getFeedbackById(Long feedbackId);
public List<Feedback> getAllFeedbacks();
public List<Feedback> getFeedbacksByUserId(Long userId);
public boolean deleteFeedback(Long feedbackId);

}