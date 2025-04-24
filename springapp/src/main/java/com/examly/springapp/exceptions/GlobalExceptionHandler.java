package com.examly.springapp.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserAlreadyExistException(UserAlreadyExistException e){
        return ResponseEntity.status(404).body(e.getMessage()); // 404 - Not Found
    }

    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<?> handlePasswordNotMatchedException(PasswordNotMatchedException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    // Feedback Exceptions----->
    @ExceptionHandler(FeedbackNotFoundException.class)
    public ResponseEntity<String> handleFeedbackNotFoundException(FeedbackNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage()); // 404 - Not Found
    }

    @ExceptionHandler(InvalidFeedbackException.class)
    public ResponseEntity<String> handleInvalidFeedbackException(InvalidFeedbackException e) {
        return ResponseEntity.status(400).body(e.getMessage()); // 400 - Bad Request
    }

   

}
