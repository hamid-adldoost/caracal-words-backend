package com.adldoost.caracallanguage.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Slf4j
@Component
public class JwtUtil {

    public String generateIAMJwt(Key key, Claims claims) {

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
    }

    public Claims getClaimsFromJwt(String jwt, Key key) {
        try {
            if(jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            throw new AccessDeniedException("token expired", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AccessDeniedException("invalid token", e);
        }
    }

    public static Key getHmacSHA512(byte[] secretKey) {
        String algorithm = "HmacSHA512";
        return new SecretKeySpec(secretKey, algorithm);
   }

}
