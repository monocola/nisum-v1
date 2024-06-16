package com.project.nisum.app.config.jwt;

import com.project.nisum.app.handlers.AuthenticationErrorHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private final AuthenticationErrorHandler errorHandler;

    public AuthEntryPointJwt(AuthenticationErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        errorHandler.handleError(request, response, authException);
    }
}