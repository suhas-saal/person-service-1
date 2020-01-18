package com.suhas.repository;

import com.suhas.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFirstNameAndLastNameAndActive(String firstName, String lastName, Boolean active);

}
