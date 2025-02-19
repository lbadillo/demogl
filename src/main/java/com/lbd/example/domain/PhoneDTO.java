package com.lbd.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
    private Long number;
    @JsonProperty("citycode")
    private int  cityCode;
    @JsonProperty("countrycode")
    private String countryCode;
}
