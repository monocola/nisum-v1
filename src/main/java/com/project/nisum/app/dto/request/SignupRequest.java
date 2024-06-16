package com.project.nisum.app.dto.request;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * SignupRequest is a data transfer object (DTO) for user signup requests.
 * It contains the user's name, email, password, and a list of phones.
 */
@Data
public class SignupRequest {

    /**
     * The user's name.
     * It must not be blank.
     */
    @NotBlank
    private String name;

    /**
     * The user's email.
     * It must not be blank and its size must not exceed 50 characters.
     */
    @NotBlank
    @Size(max = 50)
    private String email;

    /**
     * The user's password.
     * It must not be blank.
     */
    @NotBlank
    private String password;

    /**
     * The list of user's phones.
     * Each phone is represented by a PhoneRequest object.
     */
    private List<PhoneRequest> phones;
}