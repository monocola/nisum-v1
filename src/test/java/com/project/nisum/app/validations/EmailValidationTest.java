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
class EmailValidationTest {

    @Autowired
    private EmailValidation emailValidation;

    @Test
    @DisplayName("Test email validation with valid email")
    void testValidateWithValidEmail() {

        String validEmail = "test@example.com";


        boolean isValid = emailValidation.validate(validEmail);


        assertTrue(isValid, "Expected email to be valid");
    }

    @Test
    @DisplayName("Test email validation with invalid email")
    void testValidateWithInvalidEmail() {

        String invalidEmail = "testexample.com";


        boolean isValid = emailValidation.validate(invalidEmail);


        assertFalse(isValid, "Expected email to be invalid");
    }

    @Test
    @DisplayName("Test get error message")
    void testGetErrorMessage() {

        String errorMessage = emailValidation.getErrorMessage();


        assertEquals(Constant.MESSAGE_EMAIL_PATTERN, errorMessage, "Expected error message to match");
    }
}