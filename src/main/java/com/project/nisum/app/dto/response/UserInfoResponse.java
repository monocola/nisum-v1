package com.project.nisum.app.dto.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * UserInfoResponse is a data transfer object (DTO) for user information responses.
 * It contains the user's id, email, token, creation timestamp, modification timestamp, last login timestamp, and active status.
 */
@Data
public class UserInfoResponse {
    /**
     * The user's id.
     */
    private UUID id;

    /**
     * The user's email.
     */
    private String email;

    /**
     * The user's token.
     */
    private String token;

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
    private boolean isactive;

    /**
     * Constructs a new UserInfoResponse using the provided Builder.
     * @param builder The Builder to use.
     */
    private UserInfoResponse(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.token = builder.token;
        this.created = builder.created;
        this.modified = builder.modified;
        this.last_login = builder.last_login;
        this.isactive = builder.isactive;
    }

    /**
     * Builder is a static inner class for building UserInfoResponse objects.
     */
    public static class Builder {
        private UUID id;
        private String email;
        private String token;
        private Timestamp created;
        private Timestamp modified;
        private Timestamp last_login;
        private boolean isactive;

        /**
         * Sets the id of the UserInfoResponse to be built.
         * @param id The id to set.
         * @return The current Builder.
         */
        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the email of the UserInfoResponse to be built.
         * @param email The email to set.
         * @return The current Builder.
         */
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the token of the UserInfoResponse to be built.
         * @param token The token to set.
         * @return The current Builder.
         */
        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        /**
         * Sets the creation timestamp of the UserInfoResponse to be built.
         * @param created The creation timestamp to set.
         * @return The current Builder.
         */
        public Builder setCreated(Timestamp created) {
            this.created = created;
            return this;
        }

        /**
         * Sets the modification timestamp of the UserInfoResponse to be built.
         * @param modified The modification timestamp to set.
         * @return The current Builder.
         */
        public Builder setModified(Timestamp modified) {
            this.modified = modified;
            return this;
        }

        /**
         * Sets the last login timestamp of the UserInfoResponse to be built.
         * @param last_login The last login timestamp to set.
         * @return The current Builder.
         */
        public Builder setLastLogin(Timestamp last_login) {
            this.last_login = last_login;
            return this;
        }

        /**
         * Sets the active status of the UserInfoResponse to be built.
         * @param isactive The active status to set.
         * @return The current Builder.
         */
        public Builder setIsActive(boolean isactive) {
            this.isactive = isactive;
            return this;
        }

        /**
         * Builds a new UserInfoResponse with the current state of the Builder.
         * @return A new UserInfoResponse.
         */
        public UserInfoResponse build() {
            return new UserInfoResponse(this);
        }
    }
}