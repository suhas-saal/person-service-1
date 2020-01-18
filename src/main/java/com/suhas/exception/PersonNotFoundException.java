package com.suhas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom @{@link PersonServiceException} for Invalid Person
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid Person ID")
public class PersonNotFoundException extends PersonServiceException {

    public PersonNotFoundException(String message) {
        super(message);
    }

}
