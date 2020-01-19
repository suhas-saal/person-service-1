package com.suhas.exception;

/**
 * Custom @{@link RuntimeException} for the @{@link com.suhas.PersonServiceApplication}
 */
public class PersonServiceException extends RuntimeException {

    public PersonServiceException(String message) {
        super(message);
    }
}
