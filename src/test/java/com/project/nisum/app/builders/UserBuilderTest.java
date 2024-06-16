package com.project.nisum.app.builders;

import com.project.nisum.app.models.Phone;
import com.project.nisum.app.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserBuilderTest {

    private UUID id;
    private String name;
    private String email;
    private Timestamp created;
    private String password;
    private Phone phone;
    private List<Phone> phones;
    private Timestamp modified;
    private Timestamp lastLogin;
    private String token;
    private boolean isActive;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        name = "Test Name";
        email = "test@example.com";
        created = new Timestamp(System.currentTimeMillis());
        password = "password";
        modified = new Timestamp(System.currentTimeMillis());
        lastLogin = new Timestamp(System.currentTimeMillis());
        token = "token";
        isActive = true;
    }


    @Test
    @DisplayName("Test UserBuilder")
    void testBuild() {

        User user = new UserBuilder()
                .setId(id)
                .setName(name)
                .setEmail(email)
                .setCreated(created)
                .setPassword(password)
                .setModified(modified)
                .setLastLogin(lastLogin)
                .setToken(token)
                .setIsActive(isActive)
                .build();

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(created, user.getCreated());
        assertEquals(password, user.getPassword());
        assertEquals(modified, user.getModified());
        assertEquals(lastLogin, user.getLastLogin());
        assertEquals(token, user.getToken());
        assertEquals(isActive, user.getIsActive());
    }
}