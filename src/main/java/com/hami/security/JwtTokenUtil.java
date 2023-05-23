package com.hami.security;


import com.hami.Entity.User;
import io.jsonwebtoken.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; //24h


    @Value("@{app.jwt.secret}")
    private String secretkey;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId() + "," + user.getEmail())
                .claim("roles", user.getRoles().toString())
                .setIssuer("Hamed Zarrabi")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretkey)
                .compact();

    }

    public boolean validateAccessToken(String token) {

        try {
            Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);

            return true;

        } catch (ExpiredJwtException e) {
            LOGGER.error("JWT expired", e);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Token is null, empty or has only whitespace", e);
        } catch (MalformedJwtException e) {
            LOGGER.error("JWT is invalid", e);
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JWT is not supported", e);
        } catch (SignatureException e) {
            LOGGER.error("Signature validation failed", e);
        }

        return false;
    }


    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretkey)
                .parseClaimsJws(token)
                .getBody();
    }
}
