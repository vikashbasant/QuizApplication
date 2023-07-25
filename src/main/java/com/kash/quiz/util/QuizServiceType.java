package com.kash.quiz.util;

public enum QuizServiceType {

    //=> For Question
    GET_ALL_QUESTION,
    ADD_QUESTION,
    DELETE_QUESTION,
    UPDATE_QUESTION,
    GET_ALL_QUESTION_BY_CATEGORY,

    //=> For Quiz:
    GET_QUIZ,
    SUBMIT_QUIZ,
    CREATE_QUIZ,

    // => Auth User:
    REGISTER_USER,
    LOGIN_USER
}
