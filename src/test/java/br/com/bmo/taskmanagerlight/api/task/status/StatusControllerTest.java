package br.com.bmo.taskmanagerlight.api.task.status;

import static org.hamcrest.Matchers.*;

import java.net.URI;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
public class StatusControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TestEntityManager em;
	
	@Test
	void shouldReturn404WhenNoStatusExistsByName() throws Exception {
		URI uri = new URI("/api/task/status/XXXXXXXX");
		mockMvc.perform(MockMvcRequestBuilders.get(uri))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void shouldReturnAStatusByName() throws Exception {
		Status status = new Status("New");
		em.persist(status);
		
		URI uri = new URI("/api/task/status/New");
		mockMvc.perform(MockMvcRequestBuilders.get(uri))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.log())
			.andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("New")));
	}
}
