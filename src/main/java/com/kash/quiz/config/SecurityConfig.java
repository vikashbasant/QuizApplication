package com.kash.quiz.config;


import com.kash.quiz.constant.SecurityConfigConstants;
import com.kash.quiz.security.CustomUserDetailService;
import com.kash.quiz.security.JwtAuthenticationEntryPoint;
import com.kash.quiz.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {


    private final CustomUserDetailService customUserDetailService;


    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.info("===: SecurityConfig:: Inside filterChain Method:===");

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(SecurityConfigConstants.PUBLIC_URL).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));


        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.authenticationProvider(daoAuthenticationProvider());

        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        log.info("===: SecurityConfig:: Inside daoAuthenticationProvider Method:===");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.customUserDetailService);
        provider.setPasswordEncoder(getPasswordEncoder());
        return provider;

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        log.info("===: SecurityConfig:: Inside getPasswordEncoder Method:===");
        return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {

            return configuration.getAuthenticationManager();

    }
}
