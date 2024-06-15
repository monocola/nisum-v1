package com.project.nisum.app.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    @DisplayName("Test Getters")
    void testSetUsername() {

        String expectedEmail = "juan@rodriguez.org";
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setUsername(expectedEmail);

        assertEquals(expectedEmail, loginRequest.getEmail());
    }

    @Test
    @DisplayName("Test Setters")
    void testSetPassword() {
        String expectedPassword = "Lima2025$";
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setPassword(expectedPassword);

        assertEquals(expectedPassword, loginRequest.getPassword());
    }
}