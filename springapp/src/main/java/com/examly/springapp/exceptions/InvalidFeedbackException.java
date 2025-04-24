package com.examly.springapp.exceptions;

public class InvalidFeedbackException extends RuntimeException {
    public InvalidFeedbackException(String msg) {
        super(msg);
    }
}
