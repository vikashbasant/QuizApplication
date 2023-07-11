package com.kash.quiz.util;

import com.kash.quiz.exception.QuizException;
import com.kash.quiz.payload.Response;

public interface QuizService {
    QuizServiceType getServiceType ();

    <T> Response executeService (T t) throws QuizException;
}
