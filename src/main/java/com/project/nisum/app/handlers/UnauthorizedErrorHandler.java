package com.project.nisum.app.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.nisum.app.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a component that handles unauthorized authentication errors.
 * It implements the AuthenticationErrorHandler interface.
 */
@Component
public class UnauthorizedErrorHandler implements AuthenticationErrorHandler{

    // Logger instance for this class
    private static final Logger logger = LoggerFactory.getLogger(UnauthorizedErrorHandler.class);

    /**
     * This method handles unauthorized authentication errors.
     * It logs the error message, sets the response status and content type,
     * and writes a JSON body to the response output stream.
     *
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @param authException the AuthenticationException
     * @throws IOException if an input or output exception occurred
     * @throws ServletException if a servlet exception occurred
     */
    @Override
    public void handleError(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Log the error message
        logger.error("Unauthorized error: {}", authException.getMessage());

        // Set the response content type to JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // Set the response status to unauthorized (401)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Create a map for the response body
        final Map<String, Object> body = new HashMap<>();
        // Put the error message in the map
        body.put(Constant.MESSAGE, Constant.MESSAGE_BAD_CREDENTIALS);

        // Create an ObjectMapper to convert the map to JSON
        final ObjectMapper mapper = new ObjectMapper();
        // Write the map as JSON to the response output stream
        mapper.writeValue(response.getOutputStream(), body);
    }
}