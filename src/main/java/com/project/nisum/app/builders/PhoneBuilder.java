package com.project.nisum.app.builders;

import com.project.nisum.app.models.Phone;
import com.project.nisum.app.models.User;

/**
 * PhoneBuilder is a builder class for creating Phone objects.
 * It follows the Builder design pattern.
 */
public class PhoneBuilder {
    private String number;
    private String cityCode;
    private String countryCode;
    private User user;

    /**
     * Sets the phone number.
     * @param number The phone number.
     * @return The current PhoneBuilder instance.
     */
    public PhoneBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    /**
     * Sets the city code.
     * @param cityCode The city code.
     * @return The current PhoneBuilder instance.
     */
    public PhoneBuilder setCityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    /**
     * Sets the country code.
     * @param countryCode The country code.
     * @return The current PhoneBuilder instance.
     */
    public PhoneBuilder setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    /**
     * Sets the user associated with the phone.
     * @param user The user.
     * @return The current PhoneBuilder instance.
     */
    public PhoneBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    /**
     * Builds and returns a Phone object.
     * @return A new Phone object.
     */
    public Phone build() {
        Phone phone = new Phone();
        phone.setNumber(this.number);
        phone.setCityCode(this.cityCode);
        phone.setCountryCode(this.countryCode);
        phone.setUser(this.user);
        return phone;
    }
}