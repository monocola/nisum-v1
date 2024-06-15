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

@Data
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private UUID id;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    private Timestamp created;
    private Timestamp modified;
    private Timestamp last_login;
    private Boolean isactive;


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return "";
    }


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
