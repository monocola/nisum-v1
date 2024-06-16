package com.project.nisum.app.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthEntryPointJwtTest {

   @Test
@DisplayName("Test commence method")
void commence() throws IOException, ServletException {
    // Arrange
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    AuthenticationException authException = mock(AuthenticationException.class);
    AuthEntryPointJwt authEntryPointJwt = new AuthEntryPointJwt();

    when(authException.getMessage()).thenReturn("Unauthorized error");

    // Mock the ServletOutputStream
    ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);
    when(response.getOutputStream()).thenReturn(servletOutputStream);

    // Act
    authEntryPointJwt.commence(request, response, authException);

    // Assert
    verify(response, times(1)).setContentType(MediaType.APPLICATION_JSON_VALUE);
    verify(response, times(1)).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    verify(authException, times(1)).getMessage();
}
}