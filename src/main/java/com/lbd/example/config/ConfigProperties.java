package com.lbd.example.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Ludwing Badillo
 * Configuration properties class.
 * Prefix in application.properties: security
 */

@Configuration
@ConfigurationProperties(prefix = "security")
@Getter
@Setter
public class ConfigProperties {
    /**
     * password to encrypt.
     */
    private String password;
    /**
     * salt to encrypt.
     */
    private String salt;

}

