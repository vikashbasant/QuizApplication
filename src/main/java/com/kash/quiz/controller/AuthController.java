package com.kash.quiz.controller;

import com.kash.quiz.exception.QuizException;
import com.kash.quiz.payload.RegisterRequest;
import com.kash.quiz.payload.Response;
import com.kash.quiz.payload.auth.JwtAuthRequest;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceFactory;
import com.kash.quiz.util.QuizServiceType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private final QuizServiceFactory factory;

    @PostMapping("/register")
    public ResponseEntity<Response> signUp(@RequestBody @Valid RegisterRequest userDTO) throws QuizException {

        LOGGER.info("=>>AuthController:: Inside singUp Method<<=");
        QuizService service = factory.getService(QuizServiceType.REGISTER_USER);
        Response response = service.executeService(userDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<Response> singIn(@RequestBody @Valid JwtAuthRequest userCredentials) throws QuizException {

        LOGGER.info("=>>AuthController:: Inside SignIn Method<<=");
        QuizService service = factory.getService(QuizServiceType.LOGIN_USER);
        Response response = service.executeService(userCredentials);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
