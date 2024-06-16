package com.project.nisum.app.dto.response;

import lombok.Data;

/**
 * MessageResponse is a data transfer object (DTO) for sending messages as responses.
 * It contains a single field, message, which holds the message to be sent.
 */
@Data
public class MessageResponse {
    /**
     * The message to be sent.
     */
    private String message;

    /**
     * Constructs a new MessageResponse with the specified message.
     * @param message The message to be sent.
     */
    public MessageResponse(String message) {
        this.message = message;
    }
}