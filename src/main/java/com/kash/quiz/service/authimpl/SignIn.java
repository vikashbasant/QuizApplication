package com.kash.quiz.service.authimpl;

import com.kash.quiz.constant.QuizConstant;
import com.kash.quiz.dto.Response;
import com.kash.quiz.dto.authdto.JwtAuthRequest;
import com.kash.quiz.dto.authdto.JwtAuthResponse;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.security.JwtTokenHelper;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class SignIn implements QuizService {

    @Autowired
    private final JwtTokenHelper jwtTokenHelper;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private final AuthenticationManager authenticationManager;


    @Override
    public QuizServiceType getServiceType() {
        return QuizServiceType.LOGIN_USER;
    }

    @Override
    public <T> Response executeService(T t) throws QuizException {
        log.info("=>>SignIn:: Inside executeService Method<<=");

        JwtAuthRequest jwtAuthRequest = (JwtAuthRequest) t;

        /*----Now call the authenticate Method for authentication----*/
        this.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());


        /*----Now call the loadUserByUsername Method for Creating UserDetails----*/
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());

        /*----Now Call the generateToken Method for generate the Token----*/
        String token = this.jwtTokenHelper.generateToken(userDetails);

        /*----Simply Return Response-----*/
        JwtAuthResponse response = new JwtAuthResponse();
        response.setStatus(QuizConstant.SUCCESS_STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Successfully generated Token.");
        response.setToken(token);

        return response;


    }

    private void authenticate(String username, String password) {

        log.info("===: SignIn:: Inside authenticate Method :===");
        UsernamePasswordAuthenticationToken authenticationToken  = new UsernamePasswordAuthenticationToken(username,
                password);
        try {
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            log.error("Invalid Details i.e UserName & Password");
            throw new BadCredentialsException("Invalid UserName or Password");
        }

    }
}
