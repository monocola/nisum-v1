package com.project.nisum.app.services.impl;


import com.project.nisum.app.builders.UserBuilder;
import com.project.nisum.app.dto.request.LoginRequest;
import com.project.nisum.app.dto.request.SignupRequest;
import com.project.nisum.app.dto.response.UserInfoResponse;
import com.project.nisum.app.models.Phone;
import com.project.nisum.app.models.User;
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

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public void createUserAndPhones(SignupRequest signUpRequest) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        List<Phone> phones = signUpRequest.getPhones();

        User user = new UserBuilder()
                .setId(signUpRequest.getId())
                .setName(signUpRequest.getName())
                .setEmail(signUpRequest.getEmail())
                .setModified(currentTime)
                .setLastLogin(currentTime)
                .setCreated(currentTime)
                .setPassword(encoder.encode(signUpRequest.getPassword()))
                .setPhones(phones)
                .setIsActive(true)
                .build();

        phones.forEach(phone -> phone.setUser(user));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserInfoResponse getUserInfo(LoginRequest loginRequest) {
        UserDetailsImpl userDetails = authenticateAndGetUserDetails(loginRequest.getEmail(), loginRequest.getPassword());
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        String token = jwtCookie.getValue();

        return buildUserInfoResponse(userDetails, token);
    }

    @Override
    public UserDetailsImpl authenticateAndGetUserDetails(String email, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Generate JWT token
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        String token = jwtCookie.getValue();

        // Update last login time and token
        User user = userRepository.findById(userDetails.getId()).orElse(null);
        if (user != null) {
            user.setLastLogin(new Timestamp(System.currentTimeMillis()));
            user.setToken(token); // Set the token
            userRepository.save(user);
        }

        return userDetails;
    }

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
