package com.suhas.exception;

/**
 * Custom @{@link RuntimeException} for the AuthenticationService
 */
public class PersonServiceException extends RuntimeException {

    public PersonServiceException(String message) {
        super(message);
    }
}
