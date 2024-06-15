package com.project.nisum.app.validations;

public interface ValidationInterface {
        boolean validate(String value);
        String getErrorMessage();
}
