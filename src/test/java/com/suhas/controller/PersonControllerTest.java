package com.suhas.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @DisplayName("Testing create person")
    //@Test
    public void testCreatePerson() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/person/")
                .param("first_name", "suhas")
                .param("last_name", "saheer")
                .param("hobbies", "")
                .param("age", "10")
                .param("favourite_colour", "red"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @DisplayName("Testing invalid request for create person")
    @Test
    public void testCreatePersonBadRequest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/person/")
                .param("first_name", "suhas")
                .param("last_name", "saheer")
                .param("hobbies", getHobbies())
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

    private String[] getHobbies() {
        String[] hobbies = new String[1];
        hobbies[0] = "football";
        System.out.println(hobbies);
        return hobbies;
    }


}
