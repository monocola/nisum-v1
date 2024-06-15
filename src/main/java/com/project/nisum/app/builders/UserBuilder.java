package com.project.nisum.app.builders;

import com.project.nisum.app.models.Phone;
import com.project.nisum.app.models.User;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/* UserBuilder class is used to create a User object with the required attributes */

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

    public UserBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setCreated(Timestamp created) {
        this.created = created;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setPhones(List<Phone> phones) {
        this.phones = phones;
        return this;
    }

    public UserBuilder setModified(Timestamp modified) {
        this.modified = modified;
        return this;
    }

    public UserBuilder setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public UserBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    public UserBuilder setIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

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
