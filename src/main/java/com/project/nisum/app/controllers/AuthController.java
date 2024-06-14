package com.project.nisum.app.controllers;

import com.project.nisum.app.dto.request.LoginRequest;
import com.project.nisum.app.dto.request.SignupRequest;
import com.project.nisum.app.dto.response.MessageResponse;
import com.project.nisum.app.dto.response.UserInfoResponse;
import com.project.nisum.app.models.User;
import com.project.nisum.app.repositories.UserRepository;
import com.project.nisum.app.services.impl.UserDetailsImpl;
import com.project.nisum.app.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    //@Autowired
    //RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        String token = jwtCookie.getValue();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getEmail(),
                        token,
                        userDetails.getCreated(),
                        userDetails.getCreated(),
                        userDetails.getCreated(),
                        true
                ));


    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        //usar patron builder
        // Create new user's account

        //obtener fecha y hora:

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        User user = new User(
                signUpRequest.getId(),
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                currentTime,
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getPhones()
        );

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
