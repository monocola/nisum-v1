package com.project.nisum.app.validations;

import com.project.nisum.app.utils.Constant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PasswordValidationTest {

    @Autowired
    private PasswordValidation passwordValidation;

    @Test
    @DisplayName("Test password validation with valid password")
    void testValidateWithValidPassword() {
        // Arrange
        String validPassword = "Lima2025$";

        // Act
        boolean isValid = passwordValidation.validate(validPassword);

        // Assert
        assertTrue(isValid, "Expected password to be valid");
    }

    @Test
    @DisplayName("Test password validation with invalid password")
    void testValidateWithInvalidPassword() {
        // Arrange
        String invalidPassword = "invalidpassword";

        // Act
        boolean isValid = passwordValidation.validate(invalidPassword);

        // Assert
        assertFalse(isValid, "Expected password to be invalid");
    }

    @Test
    @DisplayName("Test get error message")
    void testGetErrorMessage() {
        // Act
        String errorMessage = passwordValidation.getErrorMessage();

        // Assert
        assertEquals(Constant.MESSAGE_PASSWORD_PATTERN, errorMessage, "Expected error message to match");
    }
}