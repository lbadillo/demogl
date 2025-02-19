package com.lbd.example.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CryptoException extends RuntimeException {


    public CryptoException(final String message
    ) {
        super(message);
        log.error("Error Message: " + message);
    }
}
