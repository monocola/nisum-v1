package com.project.nisum.app.services.impl;


import com.project.nisum.app.builders.PhoneBuilder;
import com.project.nisum.app.builders.UserBuilder;
import com.project.nisum.app.dto.request.LoginRequest;
import com.project.nisum.app.dto.request.PhoneRequest;
import com.project.nisum.app.dto.request.SignupRequest;
import com.project.nisum.app.dto.response.UserInfoResponse;
import com.project.nisum.app.models.Phone;
import com.project.nisum.app.models.User;
import com.project.nisum.app.repositories.PhoneRepository;
import com.project.nisum.app.repositories.UserRepository;
import com.project.nisum.app.services.UserService;
import com.project.nisum.app.utils.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UserServiceImpl is a service class that implements the UserService interface.
 * It provides methods for creating a user and their phones, loading a user by their email, checking if a user exists by email, getting user info, authenticating and getting user details, and building a user info response.
 */
@Service
public class UserServiceImpl  implements UserService {


    /**
     * The UserRepository for accessing user data.
     */
    @Autowired
    UserRepository userRepository;


    /**
     * The PhoneRepository for accessing phone data.
     */
    @Autowired
    PhoneRepository phoneRepository;


    /**
     * The PasswordEncoder for encoding passwords.
     */
    @Autowired
    PasswordEncoder encoder;


    /**
     * The JwtUtil for working with JWT tokens.
     */
    @Autowired
    JwtUtil jwtUtils;


    /**
     * The AuthenticationManager for authenticating users.
     */
    @Autowired
    AuthenticationManager authenticationManager;



    /**
     * Creates a user and their phones.
     * @param signUpRequest The signup request containing the user and phone data.
     */
    @Override
    public void createUserAndPhones(SignupRequest signUpRequest) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        List<PhoneRequest> phones = signUpRequest.getPhones();

        User user = new UserBuilder()
                .setName(signUpRequest.getName())
                .setEmail(signUpRequest.getEmail())
                .setModified(currentTime)
                .setLastLogin(currentTime)
                .setCreated(currentTime)
                .setPassword(encoder.encode(signUpRequest.getPassword()))
                .setIsActive(true)
                .build();

        userRepository.save(user);

        List<Phone> phoneEntities = phones.stream().map(phoneRequest ->
                new PhoneBuilder()
                        .setNumber(phoneRequest.getNumber())
                        .setCityCode(phoneRequest.getCityCode())
                        .setCountryCode(phoneRequest.getCountryCode())
                        .setUser(user)
                        .build()
        ).collect(Collectors.toList());

        phoneRepository.saveAll(phoneEntities);
    }


    /**
     * Loads a user by their email.
     * @param email The email of the user to load.
     * @return The UserDetails of the loaded user.
     * @throws UsernameNotFoundException if no user is found with the given email.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Correo no encontrado: " + email));

        return UserDetailsImpl.build(user);
    }


    /**
     * Checks if a user exists with the given email.
     * @param email The email to check.
     * @return true if a user exists with the given email, false otherwise.
     */
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    /**
     * Gets the user info for the given login request.
     * @param loginRequest The login request containing the user's email and password.
     * @return The user info response.
     */
    @Override
    public UserInfoResponse getUserInfo(LoginRequest loginRequest) {
    return Optional.of(loginRequest)
            .map(request -> {
                UserDetailsImpl userDetails = authenticateAndGetUserDetails(request.getEmail(), request.getPassword());
                ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
                String token = jwtCookie.getValue();
                return buildUserInfoResponse(userDetails, token);
            })
            .orElseThrow(() -> new IllegalArgumentException("Ocurrió un error al intentar logearse."));
    }


    /**
     * Authenticates a user and gets their details.
     * @param email The user's email.
     * @param password The user's password.
     * @return The UserDetails of the authenticated user.
     */
   @Override
    public UserDetailsImpl authenticateAndGetUserDetails(String email, String password) {
        Authentication authentication = authenticateUser(email, password);
        UserDetailsImpl userDetails = getUserDetails(authentication);
        String token = generateJwtToken(userDetails);
        updateUserLastLoginAndToken(userDetails, token);
        return userDetails;
    }

    /**
     * Authenticates a user with the given email and password.
     * @param email The email of the user to authenticate.
     * @param password The password of the user to authenticate.
     * @return The Authentication of the authenticated user.
     */
    private Authentication authenticateUser(String email, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    /**
     * Gets the UserDetails from the given Authentication.
     * @param authentication The Authentication to get the UserDetails from.
     * @return The UserDetails of the given Authentication.
     */
    private UserDetailsImpl getUserDetails(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (UserDetailsImpl) authentication.getPrincipal();
    }

    /**
     * Generates a JWT token for the given UserDetails.
     * @param userDetails The UserDetails to generate the token for.
     * @return The generated JWT token.
     */
    private String generateJwtToken(UserDetailsImpl userDetails) {
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        return jwtCookie.getValue();
    }

    /**
     * Updates the last login and token for the given UserDetails.
     * @param userDetails The UserDetails to update.
     * @param token The token to set.
     */
    private void updateUserLastLoginAndToken(UserDetailsImpl userDetails, String token) {
        Optional.ofNullable(userRepository.findById(userDetails.getId()).orElse(null))
                .ifPresent(user -> {
                    user.setLastLogin(new Timestamp(System.currentTimeMillis()));
                    user.setToken(token); // Set the token
                    userRepository.save(user);
                });
    }


    /**
     * Builds a user info response from the given UserDetails and token.
     * @param userDetails The UserDetails to build from.
     * @param token The token.
     * @return The user info response.
     */
    @Override
    public UserInfoResponse buildUserInfoResponse(UserDetailsImpl userDetails, String token) {
        return new UserInfoResponse.Builder()
                .setId(userDetails.getId())
                .setEmail(userDetails.getEmail())
                .setToken(token)
                .setCreated(userDetails.getCreated())
                .setModified(userDetails.getModified())
                .setLastLogin(userDetails.getLast_login())
                .setIsActive(userDetails.getIsactive())
                .build();


    }



}
