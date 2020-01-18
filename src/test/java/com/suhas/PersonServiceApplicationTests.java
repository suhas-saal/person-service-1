package com.suhas;

import com.suhas.controller.PersonController;
import com.suhas.service.IPersonService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceApplicationTests {

	@Autowired
	private PersonController personController;

	@Autowired
	private IPersonService personService;

	@Test
	public void contextLoads() {
		Assertions.assertThat(personController).isNotNull();
		Assertions.assertThat(personService).isNotNull();
	}

}
