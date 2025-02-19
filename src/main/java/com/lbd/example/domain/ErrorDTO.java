package com.lbd.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDTO {
    /**
     * error code.
     */
    private int code;
    /**
     * error description.
     */
    @JsonProperty("detail")
    private String description;
    /**
     *  timestamp.
     */
    private LocalDateTime timestamp;
}
