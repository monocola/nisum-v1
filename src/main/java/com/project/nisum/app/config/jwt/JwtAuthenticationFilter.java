package com.project.nisum.app.config.jwt;

import com.project.nisum.app.services.impl.UserDetailsServiceImpl;
import com.project.nisum.app.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

/**
 * JwtAuthenticationFilter is a class that extends AuthenticationFilter.
 * It is responsible for parsing, validating JWT tokens, loading user details, and authenticating the user.
 */
public class JwtAuthenticationFilter extends AuthenticationFilter {

    @Autowired
    private JwtUtil jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Parses the JWT token from the HTTP request.
     *
     * @param request the HTTP request
     * @return the JWT token as a string
     */
    @Override
    protected String parseToken(HttpServletRequest request) {
        return jwtUtils.getJwtFromCookies(request);
    }

    /**
     * Validates the JWT token.
     *
     * @param token the JWT token
     * @return true if the token is valid, false otherwise
     */
    @Override
    protected boolean validateToken(String token) {
        return jwtUtils.validateJwtToken(token);
    }

    /**
     * Loads the user details from the JWT token.
     *
     * @param token the JWT token
     * @return the UserDetails object
     */
    @Override
    protected UserDetails loadUserDetails(String token) {
        String email = jwtUtils.getUserNameFromJwtToken(token);
        return userDetailsService.loadUserByUsername(email);
    }

    /**
     * Authenticates the user.
     *
     * @param request the HTTP request
     * @param userDetails the UserDetails object
     */
    @Override
    protected void authenticateUser(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}