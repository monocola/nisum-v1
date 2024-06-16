package com.project.nisum.app.services.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.nisum.app.models.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * UserDetailsImpl is a class that implements the UserDetails interface for Spring Security.
 * It contains the user's id, email, password, authorities, creation timestamp, modification timestamp, last login timestamp, and active status.
 * It also provides methods for getting the authorities, password, and username, as well as a static method for building a UserDetailsImpl from a User.
 */
@Data
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * The user's id.
     */
    private UUID id;

    /**
     * The user's email.
     */
    private String email;

    /**
     * The user's password.
     * It is ignored by Jackson during serialization.
     */
    @JsonIgnore
    private String password;

    /**
     * The authorities granted to the user.
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Returns the authorities granted to the user.
     * @return A list of the authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * The timestamp when the user was created.
     */
    private Timestamp created;

    /**
     * The timestamp when the user was last modified.
     */
    private Timestamp modified;

    /**
     * The timestamp of the user's last login.
     */
    private Timestamp last_login;

    /**
     * The user's active status.
     */
    private Boolean isactive;

    /**
     * Returns the user's password.
     * @return The password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the user's username.
     * @return An empty string.
     */
    @Override
    public String getUsername() {
        return "";
    }

    /**
     * Constructs a new UserDetailsImpl with the specified id, username, email, password, creation timestamp, modification timestamp, last login timestamp, and active status.
     * @param id The id.
     * @param username The username.
     * @param email The email.
     * @param password The password.
     * @param created The creation timestamp.
     * @param modified The modification timestamp.
     * @param last_login The last login timestamp.
     * @param isactive The active status.
     */
    public UserDetailsImpl(UUID id, String username,
                           String email, String password, Timestamp created,
                           Timestamp modified, Timestamp last_login, Boolean isactive) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.created = created;
        this.modified = modified;
        this.last_login = last_login;
        this.isactive = isactive;
    }

    /**
     * Builds a new UserDetailsImpl from the specified User.
     * @param user The User to build from.
     * @return A new UserDetailsImpl.
     */
    public static UserDetailsImpl build(User user) {

        return new UserDetailsImpl(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreated(),
                user.getModified(),
                user.getLastLogin(),
                user.getIsActive());
    }

}