package com.kash.quiz.constant;

import org.springframework.http.HttpHeaders;

public class AuthenticationFilterConstant {
    private AuthenticationFilterConstant(){}

    public static final String HEADER_AUTHORIZATION = HttpHeaders.AUTHORIZATION;
    public static final String BEARER_TOKEN_START = "Bearer ";
}
