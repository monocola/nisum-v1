package com.project.nisum.app.dto.request;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupRequestTest {


    @Test
    void setName_withValidName_setsName() {
        String expectedName = "Valid Name";
        SignupRequest signupRequest = new SignupRequest();

        signupRequest.setName(expectedName);

        assertEquals(expectedName, signupRequest.getName());
    }



    @Test
    void setEmail_withValidEmail_setsEmail() {
        // Arrange
        String expectedEmail = "test@example.com";
        SignupRequest signupRequest = new SignupRequest();

        // Act
        signupRequest.setEmail(expectedEmail);

        // Assert
        assertEquals(expectedEmail, signupRequest.getEmail());
    }



    @Test
    void setPassword_withValidPassword_setsPassword() {
        // Arrange
        String expectedPassword = "password123";
        SignupRequest signupRequest = new SignupRequest();

        // Act
        signupRequest.setPassword(expectedPassword);

        // Assert
        assertEquals(expectedPassword, signupRequest.getPassword());
    }


}