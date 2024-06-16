package com.project.nisum.app.validations;

/**
 * ValidationInterface is an interface that provides a contract for validation classes.
 * It declares methods for validating a value and getting an error message.
 */
public interface ValidationInterface {

    /**
     * Validates a value.
     * @param value The value to validate.
     * @return true if the value is valid, false otherwise.
     */
    boolean validate(String value);

    /**
     * Gets the error message for an invalid value.
     * @return The error message.
     */
    String getErrorMessage();
}