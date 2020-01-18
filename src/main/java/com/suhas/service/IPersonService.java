package com.suhas.service;

import com.suhas.dto.PersonRequest;
import com.suhas.model.Person;

import java.util.List;

public interface IPersonService {

    /**
     * Create a new person @{@link Person}
     */
    Person create(PersonRequest request);

    /**
     * Check if person already exists
     */
    boolean checkIfPersonExists(Long id);

    /**
     * Update a person  @{@link Person}
     */
    Person update(Long id, PersonRequest request);

    /**
     * Delete a person
     */
    void delete(Long id);

    /**
     * Delete all person
     */
    void deleteAll();

    /**
     * Get All Persons @{@link Person}
     * @return persons
     */
    List<Person> getAll();
}
