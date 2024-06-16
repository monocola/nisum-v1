package com.project.nisum.app.controllers;

import com.project.nisum.app.dto.request.LoginRequest;
import com.project.nisum.app.dto.request.SignupRequest;
import com.project.nisum.app.dto.response.MessageResponse;
import com.project.nisum.app.services.UserService;
import com.project.nisum.app.services.impl.UserDetailsImpl;
import com.project.nisum.app.utils.Constant;
import com.project.nisum.app.utils.JwtUtil;
import com.project.nisum.app.validations.EmailValidation;
import com.project.nisum.app.validations.PasswordValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

/**
 * AuthController is a REST controller for handling authentication requests.
 * It provides endpoints for user authentication and registration.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Api Auth", description = "Api authentication user")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userDetailsService;
    @Autowired
    JwtUtil jwtUtils;

    @Autowired
    private EmailValidation emailValidationStrategy;

    @Autowired
    private PasswordValidation passwordValidationStrategy;

    /**
     * Authenticates a user.
     * @param loginRequest The login request.
     * @return A ResponseEntity with the authentication result.
     */
    @PostMapping("/signIn")
    @Operation(summary = "Authenticate user", description = "This endpoint is used for authenticating a user")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        if (!emailValidationStrategy.validate(loginRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse(emailValidationStrategy.getErrorMessage()));
        }

        UserDetailsImpl userDetails = userDetailsService.authenticateAndGetUserDetails(
                loginRequest.getEmail(), loginRequest.getPassword());
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(userDetailsService.buildUserInfoResponse(userDetails, jwtCookie.getValue()));
    }

    /**
     * Registers a new user.
     * @param signUpRequest The signup request.
     * @return A ResponseEntity with the registration result.
     */
    @PostMapping("/signUp")
    @Operation(summary = "Register a new user", description = "This endpoint is used for registering a new user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userDetailsService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse(Constant.MESSAGE_EMAIL_EXISTS));
        }

        if (!emailValidationStrategy.validate(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse(emailValidationStrategy.getErrorMessage()));
        }

        if (!passwordValidationStrategy.validate(signUpRequest.getPassword())) {
            return ResponseEntity.badRequest().body(new MessageResponse(passwordValidationStrategy.getErrorMessage()));
        }
        userDetailsService.createUserAndPhones(signUpRequest);
        return ResponseEntity.ok(new MessageResponse(Constant.MESSAGE_USER_REGISTERED));
    }

}