package com.bcu.alumnus.exception;

public class PermissionDeniedExpectation extends RuntimeException {
    public PermissionDeniedExpectation() {
        super("权限不足");
    }
}
