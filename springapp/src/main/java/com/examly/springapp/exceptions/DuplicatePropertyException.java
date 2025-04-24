package com.examly.springapp.exceptions;
public class DuplicatePropertyException extends RuntimeException {
    
    public DuplicatePropertyException(String msg){
        super(msg);
    }
}
