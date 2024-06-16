package com.project.nisum.app.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LoginRequestTest {

    private static final Logger log = LoggerFactory.getLogger(LoginRequestTest.class);

    @Test
    @DisplayName("Test Getters")
    void testSetUsername() {

        String expectedEmail = "juan@rodriguez.org";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(expectedEmail);
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