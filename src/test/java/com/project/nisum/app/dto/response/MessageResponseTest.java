package com.project.nisum.app.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageResponseTest {

    @Test
    @DisplayName("getMessage returns correct message")
    void getMessage_returnsCorrectMessage() {
        // Arrange
        String expectedMessage = "User registered successfully";
        MessageResponse messageResponse = new MessageResponse(expectedMessage);

        // Act
        String actualMessage = messageResponse.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("setMessage sets correct message")
    void setMessage_setsCorrectMessage() {
        // Arrange
        String expectedMessage = "User registered successfully";
        MessageResponse messageResponse = new MessageResponse("");

        // Act
        messageResponse.setMessage(expectedMessage);
        String actualMessage = messageResponse.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }
}