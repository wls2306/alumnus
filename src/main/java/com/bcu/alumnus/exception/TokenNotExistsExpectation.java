package com.bcu.alumnus.exception;

public class TokenNotExistsExpectation extends RuntimeException{
    public TokenNotExistsExpectation() {
        super("Token不存在");
    }
}
