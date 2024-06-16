package com.project.nisum.app.builders;

import com.project.nisum.app.models.Phone;
import com.project.nisum.app.models.User;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * UserBuilder is a builder class for creating User objects.
 * It follows the Builder design pattern.
 */
@Getter
@Setter
public class UserBuilder {
    private UUID id;
    private String name;
    private String email;
    private Timestamp created;
    private String password;
    private List<Phone> phones;
    private Timestamp modified;
    private Timestamp lastLogin;
    private String token;
    private boolean isActive;

    /**
     * Sets the user's ID.
     * @param id The user's ID.
     * @return The current UserBuilder instance.
     */
    public UserBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the user's name.
     * @param name The user's name.
     * @return The current UserBuilder instance.
     */
    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the user's email.
     * @param email The user's email.
     * @return The current UserBuilder instance.
     */
    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Sets the user's creation timestamp.
     * @param created The user's creation timestamp.
     * @return The current UserBuilder instance.
     */
    public UserBuilder setCreated(Timestamp created) {
        this.created = created;
        return this;
    }

    /**
     * Sets the user's password.
     * @param password The user's password.
     * @return The current UserBuilder instance.
     */
    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Sets the user's phones.
     * @param phones The user's phones.
     * @return The current UserBuilder instance.
     */
    public UserBuilder setPhones(List<Phone> phones) {
        this.phones = phones;
        return this;
    }

    /**
     * Sets the user's modification timestamp.
     * @param modified The user's modification timestamp.
     * @return The current UserBuilder instance.
     */
    public UserBuilder setModified(Timestamp modified) {
        this.modified = modified;
        return this;
    }

    /**
     * Sets the user's last login timestamp.
     * @param lastLogin The user's last login timestamp.
     * @return The current UserBuilder instance.
     */
    public UserBuilder setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    /**
     * Sets the user's token.
     * @param token The user's token.
     * @return The current UserBuilder instance.
     */
    public UserBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * Sets the user's active status.
     * @param isActive The user's active status.
     * @return The current UserBuilder instance.
     */
    public UserBuilder setIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    /**
     * Builds and returns a User object.
     * @return A new User object.
     */
    public User build() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setCreated(this.created);
        user.setPassword(this.password);
        user.setPhones(this.phones);
        user.setModified(this.modified);
        user.setLastLogin(this.lastLogin);
        user.setToken(this.token);
        user.setIsActive(this.isActive);
        return user;
    }
}