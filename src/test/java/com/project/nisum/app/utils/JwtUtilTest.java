package com.project.nisum.app.utils;

import com.project.nisum.app.services.impl.UserDetailsImpl;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseCookie;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.mock.web.MockHttpServletRequest;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("Test getJwtFromCookies with existing JWT cookie")
    void testGetJwtFromCookies() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Cookie jwtCookie = new Cookie(jwtUtil.getCleanJwtCookie().getName(), "testToken"); // use the actual JWT cookie name
        request.setCookies(jwtCookie);

        String jwt = jwtUtil.getJwtFromCookies(request);

        assertNotNull(jwt, "JWT should not be null");
        assertEquals("testToken", jwt, "Expected JWT to match");
    }

    @Test
    @DisplayName("Test generateJwtCookie")
    void testGenerateJwtCookie() {
        UUID id = UUID.fromString("c0a81206-901f-18ce-8190-1f79de620000");
        String username = "testUsername";
        String email = "testEmail";
        String password = "testPassword";
        Timestamp created = new Timestamp(System.currentTimeMillis());
        Timestamp modified = new Timestamp(System.currentTimeMillis());
        Timestamp last_login = new Timestamp(System.currentTimeMillis());
        Boolean isactive = true;

        UserDetailsImpl userPrincipal = new UserDetailsImpl(id, username, email, password, created, modified, last_login, isactive);


        ResponseCookie cookie = jwtUtil.generateJwtCookie(userPrincipal);


        assertNotNull(cookie, "Expected cookie to be not null");
    }

    @Test
    @DisplayName("Test getCleanJwtCookie")
    void testGetCleanJwtCookie() {

        ResponseCookie cookie = jwtUtil.getCleanJwtCookie();


        assertNotNull(cookie, "Expected cookie to be not null");
    }

    @Test
    @DisplayName("Test getUserNameFromJwtToken")
    void testGetUserNameFromJwtToken() {

        String token = jwtUtil.generateTokenFromEmail("testUser");

        String username = jwtUtil.getUserNameFromJwtToken(token);


        assertEquals("testUser", username, "Expected username to match");
    }

    @Test
    @DisplayName("Test validateJwtToken with valid token")
    void testValidateJwtTokenWithValidToken() {

        String token = jwtUtil.generateTokenFromEmail("testUser");


        boolean isValid = jwtUtil.validateJwtToken(token);


        assertTrue(isValid, "Expected token to be valid");
    }

    @Test
    @DisplayName("Test validateJwtToken with invalid token")
    void testValidateJwtTokenWithInvalidToken() {

        String token = "invalidToken";


        boolean isValid = jwtUtil.validateJwtToken(token);


        assertFalse(isValid, "Expected token to be invalid");
    }

    @Test
    @DisplayName("Test generateTokenFromEmail")
    void testGenerateTokenFromEmail() {

        String email = "testUser";


        String token = jwtUtil.generateTokenFromEmail(email);


        assertNotNull(token, "Expected token to be not null");
    }
}