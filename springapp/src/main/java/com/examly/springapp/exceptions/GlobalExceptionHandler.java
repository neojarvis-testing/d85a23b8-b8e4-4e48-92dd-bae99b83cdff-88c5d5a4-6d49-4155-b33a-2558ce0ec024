package com.examly.springapp.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler to manage various application exceptions.
 * It ensures consistent and meaningful error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles cases where a user already exists in the system.
     * @param e UserAlreadyExistException
     * @return ResponseEntity with HttpStatus.NOT_FOUND and error message
     */
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * Handles cases where the provided password does not match the expected value.
     * @param e PasswordNotMatchedException
     * @return ResponseEntity with HttpStatus.CONFLICT and error message
     */
    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<String> handlePasswordNotMatchedException(PasswordNotMatchedException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /**
     * Handles cases where feedback is not found.
     * @param e FeedbackNotFoundException
     * @return ResponseEntity with HttpStatus.NOT_FOUND and error message
     */
    @ExceptionHandler(FeedbackNotFoundException.class)
    public ResponseEntity<String> handleFeedbackNotFoundException(FeedbackNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * Handles cases where feedback is invalid.
     * @param e InvalidFeedbackException
     * @return ResponseEntity with HttpStatus.BAD_REQUEST and error message
     */
    @ExceptionHandler(InvalidFeedbackException.class)
    public ResponseEntity<String> handleInvalidFeedbackException(InvalidFeedbackException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Handles cases where a duplicate property exists.
     * @param e DuplicatePropertyException
     * @return ResponseEntity with HttpStatus.FORBIDDEN and error message
     */
    @ExceptionHandler(DuplicatePropertyException.class)
    public ResponseEntity<String> duplicateProperty(DuplicatePropertyException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    /**
     * Handles general property-related exceptions.
     * @param e PropertyException
     * @return ResponseEntity with HttpStatus.FORBIDDEN and error message
     */
    @ExceptionHandler(PropertyException.class)
    public ResponseEntity<String> propertyException(PropertyException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    /**
     * Handles cases where a user is not found.
     * 
     * @param e UserNotFoundException
     * @return ResponseEntity with HttpStatus.INTERNAL_SERVER_ERROR and error message
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    /**
     * Handles cases where an inquiry is not found.
     * 
     * @param e InquiryNotFound
     * @return ResponseEntity with HttpStatus.INTERNAL_SERVER_ERROR and error message
     */
    @ExceptionHandler(InquiryNotFound.class)
    public ResponseEntity<String> inquiryNotFound(InquiryNotFound e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    /**
     * Handles validation errors for method arguments.
     * Collects field-specific error messages and returns a structured response.
     * 
     * @param e MethodArgumentNotValidException
     * @return ResponseEntity with HttpStatus.BAD_REQUEST and a map of field errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        Map<String, String> errorMap = new HashMap<>();

        for (FieldError err : errors) {
            errorMap.put(err.getField(), err.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap.toString());
    }
}
