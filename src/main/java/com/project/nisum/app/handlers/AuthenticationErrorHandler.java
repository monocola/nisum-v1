package com.project.nisum.app.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

public interface AuthenticationErrorHandler {
    void handleError(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException;
}
