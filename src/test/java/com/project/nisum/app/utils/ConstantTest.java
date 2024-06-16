package com.project.nisum.app.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConstantTest {

    @Test
    @DisplayName("Test MESSAGE_EMAIL_EXISTS constant")
    void testMessageEmailExists() {
        // Arrange
        String expected = "Error: El correo ya registrado.";

        // Act
        String actual = Constant.MESSAGE_EMAIL_EXISTS;

        // Assert
        assertEquals(expected, actual, "Expected MESSAGE_EMAIL_EXISTS to match");
    }

    @Test
    @DisplayName("Test MESSAGE_EMAIL_PATTERN constant")
    void testMessageEmailPattern() {
        // Arrange
        String expected = "Error: El formato del correo es inválido.";

        // Act
        String actual = Constant.MESSAGE_EMAIL_PATTERN;

        // Assert
        assertEquals(expected, actual, "Expected MESSAGE_EMAIL_PATTERN to match");
    }

    // Similar tests can be written for the rest of the constants
}