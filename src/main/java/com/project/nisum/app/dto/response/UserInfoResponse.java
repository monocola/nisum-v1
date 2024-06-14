package com.project.nisum.app.dto.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class UserInfoResponse {
    private UUID id;
    private String username;
    private String email;
    private String token;
    private Timestamp created;
    private Timestamp modified;
    private Timestamp last_login;
    private boolean isactive;

    public UserInfoResponse(UUID id, String email,
                            String token, Timestamp created, Timestamp modified,
                            Timestamp last_login, boolean isactive) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.created = created;
        this.modified = modified;
        this.last_login = last_login;
        this.isactive = isactive;
    }

}
