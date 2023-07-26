package com.kash.quiz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private final JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        log.info("===: JwtAuthenticationFilter:: Inside doFilterInternal Method :===");

        final String requestToken = request.getHeader("Authorization"); // Token is on the form of Bearer 2353454sdg
        log.info("requestToken = " + requestToken);

        final String userEmail;
        final String token;

        if (requestToken == null || !requestToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = requestToken.substring(7);
        try {
            userEmail = jwtTokenHelper.getUsernameFromToken(token);
        } catch (ExpiredJwtException ex) {
            handleJwtException(response, HttpServletResponse.SC_UNAUTHORIZED, "JWT Token has expired");
            return;
        } catch (UnsupportedJwtException ex) {
            handleJwtException(response, HttpServletResponse.SC_BAD_REQUEST, "Unsupported JWT Token");
            return;
        } catch (MalformedJwtException ex) {
            handleJwtException(response, HttpServletResponse.SC_BAD_REQUEST, "Malformed JWT Token");
            return;
        } catch (IllegalArgumentException ex) {
            handleJwtException(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
            return;
        } catch (SignatureException ex) {
            handleJwtException(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token Signature");
            return;
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (Boolean.TRUE.equals(jwtTokenHelper.isValidToken(token, userDetails))) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                handleJwtException(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token, Validation Fails!!");
                return;
            }

        }
        filterChain.doFilter(request, response);
    }

    private void handleJwtException(HttpServletResponse response, int status, String errorMessage) throws IOException {
        response.setStatus(status);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "FAILURE");
        errorResponse.put("errorMessage", errorMessage);
        errorResponse.put("statusCode", String.valueOf(status));
        errorResponse.put("responseType", "E");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
}