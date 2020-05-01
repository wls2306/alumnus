package com.bcu.alumnus.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.RSA;
import com.bcu.alumnus.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.security.*;
import java.security.cert.X509Certificate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static RSA rsa=new RSA(RSAUtils.getKeyFromFile("key.pri"),RSAUtils.getKeyFromFile("key.pub"));


    public static String createJwtToken(User u)
    {

        Map claims= new HashMap<>();
        claims.put("userId",u.getUserId());
        claims.put("userOpenId",u.getUserOpenId());
        claims.put("userClassId",u.getUserClassId());
        claims.put("userPartId",u.getUserPartId());
        claims.put("userType",u.getUserType());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(new Date().getTime()+2*60*60*1000))
                .setSubject(u.getUserId())
                .signWith(rsa.getPrivateKey())
                .compact();
    }

    public static Claims resolveToken(String token)throws Exception {
        Jws<Claims> jws;
            jws = Jwts.parserBuilder()  // (1)
                    .setSigningKey(rsa.getPublicKey())         // (2)
                    .build()                    // (3)
                    .parseClaimsJws(token); // (4)
                    return jws.getBody();
            // we can safely trust the JWT
    }
}

