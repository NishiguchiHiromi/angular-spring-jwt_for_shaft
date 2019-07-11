package com.example.mySource.auth;

import com.example.mySource.constant.CommonConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class TokenCreater {
    public static String createToken( String idStr){
        return Jwts.builder()
                .setSubject(idStr)
                .setExpiration(new Date(System.currentTimeMillis() + CommonConstant.JWT_EXPIRE_MINUTES * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, CommonConstant.CRYPT_KEY.getBytes())
                .compact();
    }
}
