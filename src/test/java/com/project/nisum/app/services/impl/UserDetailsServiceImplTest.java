package com.project.nisum.app.services.impl;

import com.project.nisum.app.models.User;
import com.project.nisum.app.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserDetailsServiceImplTest {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Mock
    UserRepository userRepository;


    @Test
    @DisplayName("Test loadUserByUsername with non-existing user")
    void testLoadUserByUsernameWithNonExistingUser() {

        when(userRepository.findByEmail("testEmail")).thenReturn(Optional.empty());


        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("testEmail"));
    }
}