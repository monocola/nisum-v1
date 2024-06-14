package com.project.nisum.app.dto.request;

import java.util.List;
import java.util.Set;

import com.project.nisum.app.models.Phone;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private List<Phone> phones;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;




}