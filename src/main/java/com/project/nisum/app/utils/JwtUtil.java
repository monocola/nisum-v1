package com.project.nisum.app.utils;

import com.project.nisum.app.services.impl.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.Date;


/**
 * JwtUtil is a utility class for working with JSON Web Tokens (JWT).
 * It provides methods for getting a JWT from cookies, generating a JWT cookie, getting a clean JWT cookie, getting a username from a JWT token, validating a JWT token, and generating a token from an email.
 */
@Component
public class JwtUtil {


    /**
     * The secret key for the JWT.
     */
    @Value("${nisum.app.jwtSecret}")
    private String jwtSecret;


    /**
     * The expiration time for the JWT in milliseconds.
     */
    @Value("${nisum.app.jwtExpirationMs}")
    private int jwtExpirationMs;


    /**
     * The name of the JWT cookie.
     */
    @Value("${nisum.app.jwtCookieName}")
    private String jwtCookie;


    /**
     * Gets a JWT from cookies.
     * @param request The HttpServletRequest containing the cookies.
     * @return The JWT if it exists, null otherwise.
     */
    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }


    /**
     * Generates a JWT cookie for the given user principal.
     * @param userPrincipal The UserDetailsImpl of the user to generate a JWT cookie for.
     * @return The generated JWT cookie.
     */
    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
        String jwt = generateTokenFromEmail(userPrincipal.getEmail());
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
        return cookie;
    }


    /**
     * Gets a clean JWT cookie.
     * @return A clean JWT cookie.
     */
    public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
        return cookie;
    }


    /**
     * Gets a username from a JWT token.
     * @param token The JWT token to get a username from.
     * @return The username.
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }


    /**
     * Generates a key for the JWT.
     * @return The generated key.
     */
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    /**
     * Validates a JWT token.
     * @param authToken The JWT token to validate.
     * @return true if the JWT token is valid, false otherwise.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            //logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            //logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            //logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            //logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }


    /**
     * Generates a JWT token from an email.
     * @param email The email to generate a JWT token from.
     * @return The generated JWT token.
     */
    public String generateTokenFromEmail(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

}
