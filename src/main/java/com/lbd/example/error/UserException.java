package com.lbd.example.error;

import lombok.extern.slf4j.Slf4j;


import static java.text.MessageFormat.format;

/**
 * Author: Ludwing Badillo
 */
@Slf4j
public class UserException extends RuntimeException {

    /**
     * New exception to manage user errors.
     * @param message
     */

    public UserException(final String message
    ) {
        super(message);
        log.error("Error Message: " + message);
    }
}
