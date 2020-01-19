package com.suhas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom @{@link PersonServiceException} for Duplicate Person
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class PersonAlreadyExistsException extends PersonServiceException {

    public PersonAlreadyExistsException(String message) {
        super(message);
    }

}
