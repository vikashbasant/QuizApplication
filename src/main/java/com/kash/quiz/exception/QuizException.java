package com.kash.quiz.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class QuizException extends RuntimeException{

    public QuizException (String message) {
        super(message);
    }

    public QuizException (String message, Throwable throwable) {
        super(message, throwable);
    }
}
