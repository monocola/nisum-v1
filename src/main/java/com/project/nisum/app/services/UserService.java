package com.project.nisum.app.services;

import com.project.nisum.app.dto.request.LoginRequest;
import com.project.nisum.app.dto.request.SignupRequest;
import com.project.nisum.app.dto.response.UserInfoResponse;
import com.project.nisum.app.services.impl.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    public void createUserAndPhones(SignupRequest signUpRequest);
    public UserDetails loadUserByUsername(String email);
    public boolean existsByEmail(String email);
    public UserInfoResponse getUserInfo(LoginRequest loginRequest);
    public UserDetailsImpl authenticateAndGetUserDetails(String email, String password);
    public UserInfoResponse buildUserInfoResponse(UserDetailsImpl userDetails, String token);

}
