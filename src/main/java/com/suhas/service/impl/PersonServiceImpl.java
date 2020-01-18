package com.suhas.service.impl;

import com.suhas.dto.PersonRequest;
import com.suhas.exception.PersonNotFoundException;
import com.suhas.model.Person;
import com.suhas.repository.PersonRepository;
import com.suhas.service.IPersonService;
import com.suhas.utility.DateUtils;
import com.suhas.utility.ResourceBundleAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Create a new person @{@link Person}
     */
    @Override
    public Person create(PersonRequest request) {
        Person person = new Person.PersonDTOBuilder(request.getFirstName(), request.getLastName())
                .withAge(request.getAge())
                .withFavColor(request.getFavouriteColor())
                .withHobby(String.join(",", request.getHobbies()))
                .withCreatedDate(DateUtils.getDate())
                .build();
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public boolean checkIfPersonExists(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Person update(Long id, PersonRequest request) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(ResourceBundleAccessor.getMessage("person.not.found")));

        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setFavouriteColor(request.getFavouriteColor());
        person.setAge(request.getAge());
        person.setHobbies(String.join(",", request.getHobbies()));
        person.setUpdatedAt(DateUtils.getDate());
        return personRepository.save(person);
    }

    @Override
    public void delete(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(ResourceBundleAccessor.getMessage("person.not.found")));
        personRepository.delete(person);
    }

    @Override
    public void deleteAll() {
        personRepository.deleteAll();
    }
}
