package com.lbd.example.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

