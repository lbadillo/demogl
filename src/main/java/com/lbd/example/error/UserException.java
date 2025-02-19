package com.lbd.example.error;

import lombok.extern.slf4j.Slf4j;


import static java.text.MessageFormat.format;

@Slf4j
public class UserException extends RuntimeException {


    public UserException(final String message
    ) {
        super(message);
        log.error("Error Message: " + message);
    }
}
