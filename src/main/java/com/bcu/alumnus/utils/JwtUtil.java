package com.bcu.alumnus.utils;

import com.bcu.alumnus.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

    public static String createJwtToken(User u)
    {
        Map claims= new HashMap<>();
        claims.put("userId",u.getUserOpenId());
        claims.put("userOpenId",u.getUserOpenId());
        claims.put("userClassId",u.getUserOpenId());
        claims.put("userPartId",u.getUserOpenId());
        claims.put("userType",u.getUserType());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(new Date().getTime()+2*60*60*1000))
                .setSubject(u.getUserId())
                .signWith(keyPair.getPrivate())
                .compact();
    }

    public static Claims resolveToken(String token)throws Exception {
        Jws<Claims> jws;

            jws = Jwts.parserBuilder()  // (1)
                    .setSigningKey(keyPair.getPublic())         // (2)
                    .build()                    // (3)
                    .parseClaimsJws(token); // (4)
                    return jws.getBody();
            // we can safely trust the JWT


    }
}

