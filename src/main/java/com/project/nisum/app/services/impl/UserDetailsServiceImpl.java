package com.project.nisum.app.services.impl;

import com.project.nisum.app.models.User;
import com.project.nisum.app.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsServiceImpl is a service class that implements the UserDetailsService interface for Spring Security.
 * It contains a UserRepository for accessing user data, and provides a method for loading a user by their email.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * The UserRepository for accessing user data.
     */
    @Autowired
    UserRepository userRepository;

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
                .orElseThrow(() ->  new UsernameNotFoundException("User Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }
}