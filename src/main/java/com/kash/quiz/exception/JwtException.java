package com.kash.quiz.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@NoArgsConstructor
@Setter
public class JwtException extends RuntimeException {

        public JwtException(String message) {
            super(message);
        }

        public JwtException(String message, Throwable throwable) {
            super(message, throwable);
        }
    }