package com.examly.springapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?>method1(UserAlreadyExistException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<?>method2(PasswordNotMatchedException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }
}
