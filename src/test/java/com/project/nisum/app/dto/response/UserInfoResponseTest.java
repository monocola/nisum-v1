package com.project.nisum.app.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoResponseTest {

    @Test
    @DisplayName("Builder builds correct UserInfoResponse")
    void builder_buildsCorrectUserInfoResponse() {

        UUID expectedId = UUID.randomUUID();
        String expectedEmail = "test@example.com";
        String expectedToken = "token";
        Timestamp expectedCreated = new Timestamp(System.currentTimeMillis());
        Timestamp expectedModified = new Timestamp(System.currentTimeMillis());
        Timestamp expectedLastLogin = new Timestamp(System.currentTimeMillis());
        boolean expectedIsActive = true;


        UserInfoResponse userInfoResponse = new UserInfoResponse.Builder()
                .setId(expectedId)
                .setEmail(expectedEmail)
                .setToken(expectedToken)
                .setCreated(expectedCreated)
                .setModified(expectedModified)
                .setLastLogin(expectedLastLogin)
                .setIsActive(expectedIsActive)
                .build();


        assertEquals(expectedId, userInfoResponse.getId());
        assertEquals(expectedEmail, userInfoResponse.getEmail());
        assertEquals(expectedToken, userInfoResponse.getToken());
        assertEquals(expectedCreated, userInfoResponse.getCreated());
        assertEquals(expectedModified, userInfoResponse.getModified());
        assertEquals(expectedLastLogin, userInfoResponse.getLast_login());
        assertEquals(expectedIsActive, userInfoResponse.isIsactive());
    }
}