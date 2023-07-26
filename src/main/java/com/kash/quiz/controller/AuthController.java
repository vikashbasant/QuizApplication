package com.kash.quiz.controller;

import com.kash.quiz.exception.QuizException;
import com.kash.quiz.dto.registoruserdto.RegisterRequestDTO;
import com.kash.quiz.dto.Response;
import com.kash.quiz.dto.authdto.JwtAuthRequest;
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


    /**
     * <b>Handles the HTTP POST request for user registration.</b>
     *
     * @param userDTO The {@link RegisterRequestDTO} containing the user registration details. It should be a valid object.
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the registration is successful, it returns HTTP status code 201 (CREATED) along with the response data.
     *         If there is an error during the registration process, it returns an appropriate HTTP status code along with the error details.
     * @throws QuizException If there is an unexpected error during the registration process.
     *
     * @see RegisterRequestDTO
     * @see ResponseEntity
     */
    @PostMapping("/register")
    public ResponseEntity<Response> signUp(@RequestBody @Valid RegisterRequestDTO userDTO) throws QuizException {

        LOGGER.info("=>>AuthController:: Inside singUp Method<<=");
        QuizService service = factory.getService(QuizServiceType.REGISTER_USER);
        Response response = service.executeService(userDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    /**
     * <b>Handles the HTTP POST request for user authentication (login).</b>
     *
     * @param userCredentials The {@link JwtAuthRequest} containing the user login credentials. It should be a valid object.
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the login is successful, it returns HTTP status code 200 (OK) along with the response data.
     *         If the provided credentials are invalid or there is an error during the authentication process,
     *         it returns an appropriate HTTP status code along with the error details.
     * @throws QuizException If there is an unexpected error during the authentication process.
     *
     * @see JwtAuthRequest
     * @see ResponseEntity
     */
    @PostMapping("/login")
    public ResponseEntity<Response> singIn(@RequestBody @Valid JwtAuthRequest userCredentials) throws QuizException {

        LOGGER.info("=>>AuthController:: Inside SignIn Method<<=");
        QuizService service = factory.getService(QuizServiceType.LOGIN_USER);
        Response response = service.executeService(userCredentials);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
