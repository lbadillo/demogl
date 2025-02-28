package com.lbd.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: Ludwing Badillo
 * Phone DTO
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
    /**
     * phone number.
     */
    private Long number;

    /**
     * City Code.
     */
    @JsonProperty("citycode")
    private int  cityCode;

    /**
     * Country Code.
     */
    @JsonProperty("countrycode")
    private String countryCode;
}
