package com.kash.quiz.util;

import com.kash.quiz.dto.Response;
import com.kash.quiz.exception.QuizException;

public interface QuizService {
    QuizServiceType getServiceType ();

    <T> Response executeService (T t) throws QuizException;
}
