package com.suhas.service;

import com.suhas.dto.PersonRequest;
import com.suhas.exception.PersonNotFoundException;
import com.suhas.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonServiceTest {

    @Autowired
    private IPersonService personService;

    @DisplayName("Test Get All Users")
    @Test
    public void testGetAllUsers() {
        personService.deleteAll();
        PersonRequest request = createPersonRequest();
        personService.create(request);
        Assert.assertTrue(personService.getAll().size() == 1);
    }

    @DisplayName("Create Person")
    @Test
    public void testCreatePerson() {
        personService.deleteAll();
        PersonRequest request = createPersonRequest();
        Assert.assertNotNull(personService.create(request));
    }


    @DisplayName("Testing Update Person")
    @Test
    public void testUpdateUser() {
        personService.deleteAll();
        PersonRequest request = createPersonRequest();
        Person person = personService.create(request);
        request.setAge("50");
        Person updatedPerson = personService.update(person.getId(), request);
        Assert.assertNotNull(updatedPerson);
        personService.delete(updatedPerson.getId());
    }

    @DisplayName("Testing Update Person")
    @Test(expected = PersonNotFoundException.class)
    public void testUpdateUserNegative() {
        personService.deleteAll();
        personService.update(100l, null);
    }


    @DisplayName("Testing Check User Exists")
    @Test
    public void testCheckIfUserExists() {
        personService.deleteAll();
        PersonRequest request = createPersonRequest();
        Person person = personService.create(request);
        Assert.assertTrue(personService.checkIfPersonExists(person.getId()));
    }

    @DisplayName("Testing Check User Exists")
    @Test
    public void testCheckIfUserExistsNotFound() {
        personService.deleteAll();
        Assert.assertFalse(personService.checkIfPersonExists(1l));
    }

    @DisplayName("Testing Delete User Not Exists")
    @Test(expected = PersonNotFoundException.class)
    public void testDeleteNegative() {
        personService.delete(999l);
    }

    private PersonRequest createPersonRequest() {
        PersonRequest request = new PersonRequest();
        request.setAge("20");
        request.setFirstName("suhas");
        request.setLastName("saheer");
        request.setFavouriteColor("red");
        request.setHobbies(getHobbies());
        return request;
    }

    private String[] getHobbies() {
        String[] hobbies = new String[1];
        hobbies[0] = "football";
        System.out.println(hobbies);
        return hobbies;
    }

}
