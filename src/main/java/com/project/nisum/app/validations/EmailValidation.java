package com.project.nisum.app.validations;

import com.project.nisum.app.utils.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * EmailValidation is a class that implements the ValidationInterface.
 * It provides methods for validating an email and getting an error message.
 */
@Component
public class EmailValidation implements ValidationInterface{

    /**
     * The pattern for validating emails.
     */
    @Value("${nisum.app.patterns.email}")
    private String emailPattern;

    /**
     * Validates an email.
     * @param email The email to validate.
     * @return true if the email matches the email pattern, false otherwise.
     */
    @Override
    public boolean validate(String email) {
        return email.matches(emailPattern);
    }

    /**
     * Gets the error message for an invalid email.
     * @return The error message.
     */
    @Override
    public String getErrorMessage() {
        return Constant.MESSAGE_EMAIL_PATTERN;
    }

}