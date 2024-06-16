package com.project.nisum.app.validations;

import com.project.nisum.app.utils.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * PasswordValidation is a class that implements the ValidationInterface.
 * It provides methods for validating a password and getting an error message.
 */
@Component
public class PasswordValidation implements ValidationInterface {

    /**
     * The pattern for validating passwords.
     */
    @Value("${nisum.app.patterns.password}")
    private String passwordPattern;

    /**
     * Validates a password.
     * @param password The password to validate.
     * @return true if the password matches the password pattern, false otherwise.
     */
    @Override
    public boolean validate(String password) {
        return password.matches(passwordPattern);
    }

    /**
     * Gets the error message for an invalid password.
     * @return The error message.
     */
    @Override
    public String getErrorMessage() {
        return Constant.MESSAGE_PASSWORD_PATTERN;
    }

}