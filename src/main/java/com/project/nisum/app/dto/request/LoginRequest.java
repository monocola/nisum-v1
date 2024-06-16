package com.project.nisum.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * LoginRequest is a data transfer object (DTO) for user login requests.
 * It contains the user's email and password.
 */
@Data
public class LoginRequest {

    /**
     * The user's email.
     * It must not be blank.
     */
    @NotBlank
    private String email;

    /**
     * The user's password.
     * It must not be blank.
     */
    @NotBlank
    private String password;
}