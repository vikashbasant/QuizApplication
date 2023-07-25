package com.kash.quiz.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authException) throws IOException {

        log.info("===: JwtAuthenticationEntryPoint:: Inside commence Method :===");
        httpServletResponse.sendError(javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED, "Access Denied!!!");
    }

}
