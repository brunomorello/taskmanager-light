package br.com.bmo.taskmanagerlight.api.task.status;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	private static String baseURI = "/api/task/status/";
	
	@Test
	void shouldReturn404WhenNoStatusExistsByName() throws Exception {
		URI uri = new URI(baseURI.concat("XXXXXXX"));
		mockMvc.perform(get(uri))
			.andExpect(status().isNotFound());
	}
	
	@Test
	void shouldReturn200AndStatusFoundByName() throws Exception {
		Status status = new Status("New");
		em.persist(status);
		
		URI uri = new URI(baseURI.concat("name=".concat(status.getName())));
		mockMvc.perform(get(uri))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", equalTo(status.getName())));
	}
	
	@Test
	void shouldReturn200AndStatusFoundById() throws Exception {
		Status status = new Status("New");
		Status statusPersist = em.persist(status);
		
		URI uri = new URI(baseURI.concat(String.valueOf(statusPersist.getId())));
		mockMvc.perform(get(uri))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", equalTo(String.valueOf(statusPersist.getId()))));
	}
	
	@Test
	void shouldReturn200AndAllStatus() throws Exception {
		URI uri = new URI(baseURI);
		mockMvc.perform(get(uri))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$..*").exists());
	}
	
	@Test
	void shouldReturn201AndStatusCreatedURI() throws Exception {
		StatusForm statusForm = new StatusForm("Reopen");
		
		URI uri = new URI(baseURI);
		
		mockMvc.perform(post(uri).content(toJsonStr(statusForm)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.name").exists())
			.andExpect(jsonPath("$.name", equalTo(statusForm.getName())));
		
	}
	
	@Test
	void shouldReturn200AndStatusUpdated() throws Exception {
		
		URI uri = new URI(baseURI.concat("1"));
		
		Status status = new Status("New");
		status.setId(1L);
		
		mockMvc.perform(put(uri).content(toJsonStr(status)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(log())
			.andExpect(jsonPath("$.name", equalTo(status.getName())));
	}
	
	@Test
	void shouldReturn200WhenStatusIsDeleted() throws Exception {
		Status status = em.persist(new Status("New"));
		
		URI uri = new URI(baseURI.concat(String.valueOf(status.getId())));
		mockMvc.perform(delete(uri))
			.andExpect(status().isOk());
	}
	
	public static String toJsonStr(final Object o) {
		try {
			return new ObjectMapper().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
