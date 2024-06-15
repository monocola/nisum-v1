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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
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

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        UserDetailsImpl userDetails = userDetailsService.authenticateAndGetUserDetails(
                loginRequest.getEmail(), loginRequest.getPassword());
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(userDetailsService.buildUserInfoResponse(userDetails, jwtCookie.getValue()));
    }


    @PostMapping("/signUp")
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
