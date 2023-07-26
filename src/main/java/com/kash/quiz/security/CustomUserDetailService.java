package com.kash.quiz.security;


import com.kash.quiz.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private final UserRepo uRepo;


    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {

        log.info("===: CustomUserDetailService:: Inside loadUserByUsername Method :===");

        log.info("userName = " + userName);

        /*----loading user from database by username----*/
        return this.uRepo.findByUserEmail(userName).orElseThrow(() -> new UsernameNotFoundException("User not " +
                "found With userEmail = " + userName));

    }
}
