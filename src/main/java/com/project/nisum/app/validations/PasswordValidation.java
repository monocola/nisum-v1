package com.project.nisum.app.validations;

import com.project.nisum.app.utils.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidation implements ValidationInterface {

    @Value("${nisum.app.patterns.password}")
    private String passwordPattern;

    @Override
    public boolean validate(String password) {
        return password.matches(passwordPattern);
    }

    @Override
    public String getErrorMessage() {
        return Constant.MESSAGE_PASSWORD_PATTERN;
    }

}
