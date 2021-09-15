package com.dropit.backend.demo.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsernameAlreadyExistsException() { super(); }

    public UsernameAlreadyExistsException(String message) { super(message); }
}
