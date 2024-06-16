package com.project.nisum.app.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Abstract class for authentication filters.
 * This class extends OncePerRequestFilter from Spring Framework to ensure a single execution per request dispatch.
 * It provides the template method doFilterInternal which contains the logic for processing authentication.
 */
public abstract class AuthenticationFilter extends OncePerRequestFilter {

    /**
     * Template method for processing authentication. It extracts the token from the request,
     * validates it, loads the user details and authenticates the user.
     * If any exception occurs during these operations, it simply logs the error and continues with the next filter in the chain.
     *
     * @param request      the HTTP request
     * @param response     the HTTP response
     * @param filterChain  the filter chain
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseToken(request);
            if (token != null && validateToken(token)) {
                UserDetails userDetails = loadUserDetails(token);
                authenticateUser(request, userDetails);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Abstract method for parsing the token from the request.
     *
     * @param request the HTTP request
     * @return the parsed token
     */
    protected abstract String parseToken(HttpServletRequest request);

    /**
     * Abstract method for validating the token.
     *
     * @param token the token to validate
     * @return true if the token is valid, false otherwise
     */
    protected abstract boolean validateToken(String token);

    /**
     * Abstract method for loading the user details from the token.
     *
     * @param token the token
     * @return the user details
     */
    protected abstract UserDetails loadUserDetails(String token);

    /**
     * Abstract method for authenticating the user.
     *
     * @param request     the HTTP request
     * @param userDetails the user details
     */
    protected abstract void authenticateUser(HttpServletRequest request, UserDetails userDetails);
}