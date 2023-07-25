package com.kash.quiz.exception;

public class JwtAuthenticationException extends RuntimeException {

    public JwtAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
