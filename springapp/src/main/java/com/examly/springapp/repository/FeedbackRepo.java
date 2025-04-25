package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;


import org.springframework.stereotype.Repository;
@Repository
public interface FeedbackRepo extends JpaRepository<Feedback,Long>{

    
    // Retrieves feedback associated with a user entity reference
    List<Feedback> findByUser(User user);

    // Custom query to fetch feedback by userId using positional parameters (?)
    @Query("SELECT feedback FROM Feedback feedback WHERE feedback.user.userId = ?1")
    List<Feedback> findByUserId(Long userId);

}

