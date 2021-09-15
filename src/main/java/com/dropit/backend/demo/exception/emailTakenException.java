package com.dropit.backend.demo.exception;

public class emailTakenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public emailTakenException() { super(); }

    public emailTakenException(String message) { super(message); }
}
