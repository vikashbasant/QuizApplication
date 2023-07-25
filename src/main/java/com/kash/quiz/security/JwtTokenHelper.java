package com.kash.quiz.security;


import com.kash.quiz.constant.JwtSecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenHelper {


    private Key getSignIngKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JwtSecurityConstants.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        log.info("===: JwtTokenHelper:: Inside getClaimFromToken Method :===");
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /*----Retrieve UserName from jwt token:----*/
    public String getUsernameFromToken(String token) {
        log.info("===: JwtTokenHelper:: Inside getUsernameFromToken Method :===");
        return getClaimFromToken(token, Claims::getSubject);
    }






    /*----For Retrieving any information from token, we will need the secret key:----*/
    private Claims getAllClaimsFromToken(String token) {
        log.info("===: JwtTokenHelper:: Inside getAllClaimsFromToken Method :===");
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignIngKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }




    /*----Generate token for user:----*/
    public String generateToken(UserDetails userDetails) {
        log.info("===: JwtTokenHelper:: Inside generateToken Method :===");
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }


    /**
     * While Creating The Token:
     * 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
     * 2. Sign the JWT using the HSS12 algorithm and secret key.
     * 3. According to JWS Compact Serialization(<a href="https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#secttion-3.1">...</a>)
     * compaction of the JWT to a URL-safe String
     */

    public String doGenerateToken(
            Map<String, Object> extraClaims,
            String subject
    ) {
        log.info("===: JwtTokenHelper:: Inside doGenerateToken Method :===");
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JwtSecurityConstants.JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSignIngKey(),SignatureAlgorithm.HS512)
                .compact();
    }


    /*----Validate Token:----*/
    public Boolean isValidToken(String token, UserDetails userDetails) {
        log.info("===: JwtTokenHelper:: Inside isValidToken Method :===");
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }




    /*----Check if the token has expired:----*/
    private Boolean isTokenExpired(String token) {
        log.info("===: JwtTokenHelper:: Inside isTokenExpired Method :===");
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /*----Retrieve Expiration date from jwt token:----*/
    public Date getExpirationDateFromToken(String token) {
        log.info("===: JwtTokenHelper:: Inside getExpirationDateFromToken Method :===");
        return getClaimFromToken(token, Claims::getExpiration);
    }


}
