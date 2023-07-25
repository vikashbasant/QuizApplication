package com.kash.quiz.constant;

public class JwtSecurityConstants {

    /*----Adding private constructor to hide the implicit public one ----*/
    private JwtSecurityConstants() {

    }
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * (long)60;

    //=> Random 512-bit encryption key, you can generate from net:
    //=> website: https://emn178.github.io/online-tools/sha512.html
    public static final String SECRET_KEY = "0fe25c2059dbaa82759a5c45e29e120f33f0ece1476f70b9cd7e7d7c31051e7c511a316ae38f48387dbe33238e8c586877e4b46f68b4478e9a59765d911c5273";
}
