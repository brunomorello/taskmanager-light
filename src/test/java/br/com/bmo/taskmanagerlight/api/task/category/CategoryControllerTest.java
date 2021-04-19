package br.com.bmo.taskmanagerlight.api.task.category;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TestEntityManager em;
	
	private static String BASE_URI = "/api/task/category/";
	
	@Test
	void shouldReturn404WhenCategoryIsNotFoundById() throws Exception {
		URI uri = new URI(BASE_URI.concat("XXXXXXX"));
		mockMvc.perform(get(uri))
			.andExpect(status().isNotFound());
	}
	
	@Test
	void shouldReturn200AndCategoryJSONWhenCategoryIsFoundById() throws Exception {
		URI uri = new URI(BASE_URI.concat("1"));
		mockMvc.perform(get(uri))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", equalTo("1")));		
	}
	
	@Test
	void shouldReturn200AndCategoryJSONWhenCategoryIsFoundByName() throws Exception {
		URI uri = new URI(BASE_URI.concat("name=".concat("Study")));
		mockMvc.perform(get(uri))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", equalTo("Study")));
	}
}
