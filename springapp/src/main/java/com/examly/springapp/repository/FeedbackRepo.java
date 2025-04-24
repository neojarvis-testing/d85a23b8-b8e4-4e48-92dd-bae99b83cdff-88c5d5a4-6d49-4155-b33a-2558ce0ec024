package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

    
    // If Feedback has a direct User entity reference:
    List<Feedback> findByUser(User user);

    @Query("SELECT feedback FROM Feedback feedback WHERE feedback.user.userId = :userId")
    List<Feedback> findByUserId(@Param("userId") Long userId);

}

