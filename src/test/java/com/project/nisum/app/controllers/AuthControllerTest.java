package com.project.nisum.app.controllers;

import com.project.nisum.app.dto.request.LoginRequest;
import com.project.nisum.app.dto.request.SignupRequest;
import com.project.nisum.app.services.UserService;
import com.project.nisum.app.services.impl.UserDetailsImpl;
import com.project.nisum.app.utils.JwtUtil;
import com.project.nisum.app.validations.EmailValidation;
import com.project.nisum.app.validations.PasswordValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userDetailsService;

    @Mock
    private JwtUtil jwtUtils;

    @Mock
    private EmailValidation emailValidationStrategy;

    @Mock
    private PasswordValidation passwordValidationStrategy;

    @InjectMocks
    private AuthController authController;

    private LoginRequest loginRequest;
    private SignupRequest signupRequest;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword("password");
    }

    @Test
@DisplayName("Test authenticateUser")
void authenticateUser() {
    // Arrange
    UUID id = UUID.randomUUID();
    String username = "testUser";
    String email = "test@example.com";
    String password = "password";
    Timestamp created = new Timestamp(System.currentTimeMillis());
    Timestamp modified = new Timestamp(System.currentTimeMillis());
    Timestamp last_login = new Timestamp(System.currentTimeMillis());
    Boolean isactive = true;

    UserDetailsImpl userDetails = new UserDetailsImpl(id, username, email, password, created, modified, last_login, isactive);

    when(emailValidationStrategy.validate(loginRequest.getEmail())).thenReturn(true);
    when(userDetailsService.authenticateAndGetUserDetails(loginRequest.getEmail(), loginRequest.getPassword()))
            .thenReturn(userDetails);

    ResponseCookie jwtCookie = ResponseCookie.from("jwt", "token").build();
    when(jwtUtils.generateJwtCookie(userDetails)).thenReturn(jwtCookie);

    ResponseEntity<?> response = authController.authenticateUser(loginRequest);

    assertEquals(200, response.getStatusCodeValue());
}

    @Test
    @DisplayName("Test registerUser")
    void registerUser() {
        // Arrange
        when(userDetailsService.existsByEmail(signupRequest.getEmail())).thenReturn(false);
        when(emailValidationStrategy.validate(signupRequest.getEmail())).thenReturn(true);
        when(passwordValidationStrategy.validate(signupRequest.getPassword())).thenReturn(true);

        // Act
        ResponseEntity<?> response = authController.registerUser(signupRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
    }
}