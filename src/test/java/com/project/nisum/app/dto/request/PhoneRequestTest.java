package com.project.nisum.app.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PhoneRequestTest {

    private PhoneRequest phoneRequest;

    @BeforeEach
    void setUp() {
        phoneRequest = new PhoneRequest();
    }

    @Test
    @DisplayName("Test number getter and setter")
    void testGetAndSetNumber() {
        String expectedNumber = "1234567890";
        phoneRequest.setNumber(expectedNumber);

        String actualNumber = phoneRequest.getNumber();

        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    @DisplayName("Test city code getter and setter")
    void testGetAndSetCityCode() {
        String expectedCityCode = "01";
        phoneRequest.setCityCode(expectedCityCode);

        String actualCityCode = phoneRequest.getCityCode();

        assertEquals(expectedCityCode, actualCityCode);
    }

    @Test
    @DisplayName("Test country code getter and setter")
    void testGetAndSetCountryCode() {
        String expectedCountryCode = "91";
        phoneRequest.setCountryCode(expectedCountryCode);

        String actualCountryCode = phoneRequest.getCountryCode();

        assertEquals(expectedCountryCode, actualCountryCode);
    }
}