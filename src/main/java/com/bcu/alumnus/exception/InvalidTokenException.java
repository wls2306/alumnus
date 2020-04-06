package com.bcu.alumnus.exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("Token非法");
    }
}
