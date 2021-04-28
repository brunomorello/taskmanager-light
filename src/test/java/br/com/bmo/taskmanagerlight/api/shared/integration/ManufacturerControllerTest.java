package br.com.bmo.taskmanagerlight.api.shared.integration;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.bmo.taskmanagerlight.api.manufacturer.ManufacturerForm;
import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;
import br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
public class ManufacturerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TestEntityManager EM;

	private static Manufacturer ACME;

	private String BASE_URI = "/api/manufacturer/";

	@BeforeEach
	void setup() {
		ACME = new Manufacturer("Acme S.A.", "ACME", "Av. Paulista");
		EM.persist(ACME);
	}

	@Test
	void shouldFindManufacturerById() throws Exception {
		mockMvc.perform(get(BASE_URI + ACME.getId())).andDo(log()).andExpect(status().isOk())
				.andExpect(jsonPath("$.displayName", equalTo(ACME.getDisplayName())));
	}

	@Test
	void shouldReturn404WhenManufacturerIsNotFoundById() throws Exception {
		mockMvc.perform(get(BASE_URI + "/231321312")).andDo(log()).andExpect(status().isNotFound());
	}

	@Test
	void shouldFindManufactureByDisplayName() throws Exception {
		mockMvc.perform(get(BASE_URI).param("displayName", ACME.getDisplayName())).andDo(log())
				.andExpect(status().isOk()).andExpect(jsonPath("$.displayName", equalTo(ACME.getDisplayName())));
	}
	
	@Test
	void shouldFindManufacturersByAddress() throws Exception {
		mockMvc.perform(get(BASE_URI).param("address", "Paulista"))
			.andDo(log())
			.andExpect(status().isOk());
	}

	@Test
	void shouldReturn201WhenManufacturerIsCreated() throws Exception {
		String payload = TaskmanagerTestUtils.toJsonStr(new ManufacturerForm("Test", "Test", "Street 1"));

		mockMvc.perform(post(BASE_URI).content(payload).contentType(MediaType.APPLICATION_JSON)).andDo(log())
				.andExpect(status().isCreated());
	}

	@Test
	void shouldReturn200WhenManufacturerIsUpdated() throws Exception {
		ACME.setAddress("Rua Augusta");
		ACME.setDisplayName("Acme Corp.");

		String payload = TaskmanagerTestUtils.toJsonStr(ACME);

		mockMvc.perform(
				put(BASE_URI.concat(ACME.getId().toString())).content(payload).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.displayName", equalTo(ACME.getDisplayName())))
			.andExpect(jsonPath("$.address", equalTo(ACME.getAddress())));
	}
}
