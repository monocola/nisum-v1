package com.project.nisum.app.validations;

import com.project.nisum.app.utils.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailValidation implements ValidationInterface{

    @Value("${nisum.app.patterns.email}")
    private String emailPattern;

    @Override
    public boolean validate(String email) {
        return email.matches(emailPattern);
    }

    @Override
    public String getErrorMessage() {
        return Constant.MESSAGE_EMAIL_PATTERN;
    }

}
