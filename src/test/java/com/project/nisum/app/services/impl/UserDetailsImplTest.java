package com.project.nisum.app.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserDetailsImplTest {

    private UserDetailsImpl userDetails;

    @BeforeEach
    void setUp() {
        UUID id = UUID.randomUUID();
        String username = "testUser";
        String email = "test@example.com";
        String password = "password";
        Timestamp created = new Timestamp(System.currentTimeMillis());
        Timestamp modified = new Timestamp(System.currentTimeMillis());
        Timestamp last_login = new Timestamp(System.currentTimeMillis());
        Boolean isactive = true;

        userDetails = new UserDetailsImpl(id, username, email, password, created, modified, last_login, isactive);
    }

    @Test
    @DisplayName("Test getAuthorities")
    void getAuthorities() {

        var authorities = userDetails.getAuthorities();


        assertTrue(authorities.isEmpty());
    }

    @Test
    @DisplayName("Test getPassword")
    void getPassword() {

        String expectedPassword = "password";


        String actualPassword = userDetails.getPassword();


        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    @DisplayName("Test getUsername")
    void getUsername() {
        // Arrange
        String expectedUsername = "";

        // Act
        String actualUsername = userDetails.getUsername();

        // Assert
        assertEquals(expectedUsername, actualUsername);
    }
}