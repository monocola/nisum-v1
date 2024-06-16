package com.project.nisum.app.config.jwt;

import com.project.nisum.app.services.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class WebSecurityConfigTest {

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private AuthEntryPointJwt unauthorizedHandler;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private WebSecurityConfig webSecurityConfig;

    @Test
    @DisplayName("Test creation of AuthTokenFilter bean")
    void authenticationJwtTokenFilter() {
        // Act
        AuthTokenFilter authTokenFilter = webSecurityConfig.authenticationJwtTokenFilter();

        // Assert
        assertNotNull(authTokenFilter);
    }

    @Test
    @DisplayName("Test creation of PasswordEncoder bean")
    void passwordEncoder() {
        PasswordEncoder passwordEncoder = webSecurityConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
    }


}