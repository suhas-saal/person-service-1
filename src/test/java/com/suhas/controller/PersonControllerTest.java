package com.suhas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.suhas.dto.PersonRequest;
import com.suhas.model.Person;
import com.suhas.service.IPersonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private IPersonService personService;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @DisplayName("Testing Create Persons")
    @Test
    public void testCreate() throws Exception {
        personService.deleteAll();
        PersonRequest request = createPersonRequest();
        personService.create(request);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);
        System.out.println("JSON :: " + requestJson);

        mockMvc.perform(post("/person/").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }


    @DisplayName("Testing Invalid Request for Create Person")
    @Test
    public void testCreatePersonBadRequest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/person/")
                .param("first_name", "suhas")
                .param("last_name", "saheer")
                .param("hobby", getHobbies())
                .param("age", "10")
                .param("favourite_colour", "red"))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @DisplayName("Testing valid search scenario where no match is found")
    @Test
    public void testSearchAllPersons() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/person/"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @DisplayName("Testing Update Person")
    @Test
    public void testUpdatePerson() throws Exception {
        personService.deleteAll();
        PersonRequest request = createPersonRequest();
        Person person = personService.create(request);
        request.setAge("99");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);
        System.out.println("JSON :: " + requestJson);

        mockMvc.perform(put("/person/" + person.getId()).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @DisplayName("Testing Delete Person")
    @Test
    public void testDeletePerson() throws Exception {
        personService.deleteAll();
        PersonRequest request = createPersonRequest();
        Person person = personService.create(request);

        mockMvc.perform(delete("/person/" + person.getId()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @DisplayName("Testing Check If Person Exists")
    @Test
    public void testCheckIfPersonExists() throws Exception {
        personService.deleteAll();
        PersonRequest request = createPersonRequest();
        Person person = personService.create(request);

        mockMvc.perform(head("/person/" + person.getId()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    private String[] getHobbies() {
        String[] hobbies = new String[1];
        hobbies[0] = "football";
        System.out.println(hobbies);
        return hobbies;
    }

    private PersonRequest createPersonRequest() {
        PersonRequest personRequest = new PersonRequest();
        personRequest.setAge("12");
        personRequest.setFavouriteColor("red");
        personRequest.setFirstName("suhas");
        personRequest.setLastName("saheer");
        String[] hobbies = new String[1];
        hobbies[0] = "football";
        personRequest.setHobbies(hobbies);
        return personRequest;
    }


}
