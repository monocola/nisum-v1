package com.project.nisum.app.services;

import com.project.nisum.app.dto.request.LoginRequest;
import com.project.nisum.app.dto.request.SignupRequest;
import com.project.nisum.app.dto.response.UserInfoResponse;
import com.project.nisum.app.services.impl.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * UserService is an interface for user services.
 * It declares methods for creating a user and their phones, loading a user by their email, checking if a user exists by email, getting user info, authenticating and getting user details, and building a user info response.
 */
public interface UserService {

    /**
     * Creates a user and their phones.
     * @param signUpRequest The signup request containing the user and phone data.
     */
    public void createUserAndPhones(SignupRequest signUpRequest);

    /**
     * Loads a user by their email.
     * @param email The email of the user to load.
     * @return The UserDetails of the loaded user.
     */
    public UserDetails loadUserByUsername(String email);

    /**
     * Checks if a user exists with the given email.
     * @param email The email to check.
     * @return true if a user exists with the given email, false otherwise.
     */
    public boolean existsByEmail(String email);

    /**
     * Gets the user info for the given login request.
     * @param loginRequest The login request containing the user's email and password.
     * @return The user info response.
     */
    public UserInfoResponse getUserInfo(LoginRequest loginRequest);

    /**
     * Authenticates a user and gets their details.
     * @param email The user's email.
     * @param password The user's password.
     * @return The UserDetails of the authenticated user.
     */
    public UserDetailsImpl authenticateAndGetUserDetails(String email, String password);

    /**
     * Builds a user info response from the given UserDetails and token.
     * @param userDetails The UserDetails to build from.
     * @param token The token.
     * @return The user info response.
     */
    public UserInfoResponse buildUserInfoResponse(UserDetailsImpl userDetails, String token);
}