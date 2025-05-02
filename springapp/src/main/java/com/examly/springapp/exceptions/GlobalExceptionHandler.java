package com.examly.springapp.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


    public ResponseEntity<?>method1(UserAlreadyExistException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
  //property Exception

  @ExceptionHandler(DuplicatePropertyException.class)
  public ResponseEntity<?>duplicateProperty(DuplicatePropertyException e){
    return ResponseEntity.status(403).body(e.getMessage());
}
@ExceptionHandler(PropertyException.class)
public ResponseEntity<?>propertyException(PropertyException e){
    return ResponseEntity.status(403).body(e.getMessage());
}

@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<?> userNotFound(UserNotFoundException e){
    return ResponseEntity.status(500).body(e.getMessage());
}

@ExceptionHandler(InquiryNotFound.class)
public ResponseEntity<?> inquiryNotFound(InquiryNotFound e){
    return ResponseEntity.status(500).body(e.getMessage());
}
@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        Map<String, String> map = new HashMap<>();
        for(FieldError err: errors){
            map.put(err.getField(), err.getDefaultMessage());
        }
        return ResponseEntity.status(400).body(map.toString());
    }
}
