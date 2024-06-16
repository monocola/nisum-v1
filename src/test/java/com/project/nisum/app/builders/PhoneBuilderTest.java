package com.project.nisum.app.builders;

import com.project.nisum.app.models.Phone;
import com.project.nisum.app.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PhoneBuilderTest {

    private String number;
    private String cityCode;
    private String countryCode;
    private User user;

    @BeforeEach
    void setUp() {
        number = "1234567890";
        cityCode = "1";
        countryCode = "57";
        user = new User();
    }

    @Test
    @DisplayName("Test PhoneBuilder")
    void testBuild() {
        Phone phone = new PhoneBuilder()
                .setNumber(number)
                .setCityCode(cityCode)
                .setCountryCode(countryCode)
                .setUser(user)
                .build();

        assertEquals(number, phone.getNumber());
        assertEquals(cityCode, phone.getCityCode());
        assertEquals(countryCode, phone.getCountryCode());
        assertEquals(user, phone.getUser());
    }
}