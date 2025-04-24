package com.examly.springapp.exceptions;

public class PasswordNotMatchedException extends RuntimeException{
    public PasswordNotMatchedException(String message){
        super(message);
    }
}
