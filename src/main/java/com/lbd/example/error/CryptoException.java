package com.lbd.example.error;

import lombok.extern.slf4j.Slf4j;

/**
 * Author: Ludwing Badillo
 */

@Slf4j
public class CryptoException extends RuntimeException {

    /**
     * new Exception to manage problem with crypto activities
     * @param message
     */
    public CryptoException(final String message
    ) {
        super(message);
        log.error("Error Message: " + message);
    }
}
