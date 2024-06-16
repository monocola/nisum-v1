package com.project.nisum.app.dto.request;

import lombok.Data;

/**
 * PhoneRequest is a data transfer object (DTO) for phone information.
 * It contains the phone's number, city code, and country code.
 */
@Data
public class PhoneRequest {

    /**
     * The phone's number.
     */
    private String number;

    /**
     * The phone's city code.
     */
    private String cityCode;

    /**
     * The phone's country code.
     */
    private String countryCode;
}