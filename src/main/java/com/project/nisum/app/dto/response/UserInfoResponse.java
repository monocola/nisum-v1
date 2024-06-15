package com.project.nisum.app.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class UserInfoResponse {
    private UUID id;
    private String email;
    private String token;
    private Timestamp created;
    private Timestamp modified;
    private Timestamp last_login;
    private boolean isactive;

    private UserInfoResponse(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.token = builder.token;
        this.created = builder.created;
        this.modified = builder.modified;
        this.last_login = builder.last_login;
        this.isactive = builder.isactive;
    }

    public static class Builder {
        private UUID id;
        private String email;
        private String token;
        private Timestamp created;
        private Timestamp modified;
        private Timestamp last_login;
        private boolean isactive;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public Builder setCreated(Timestamp created) {
            this.created = created;
            return this;
        }

        public Builder setModified(Timestamp modified) {
            this.modified = modified;
            return this;
        }

        public Builder setLastLogin(Timestamp last_login) {
            this.last_login = last_login;
            return this;
        }

        public Builder setIsActive(boolean isactive) {
            this.isactive = isactive;
            return this;
        }

        public UserInfoResponse build() {
            return new UserInfoResponse(this);
        }
    }
}