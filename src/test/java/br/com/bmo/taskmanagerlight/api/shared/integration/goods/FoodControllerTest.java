package br.com.bmo.taskmanagerlight.api.shared.integration.goods;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.hamcrest.Matchers;
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

import br.com.bmo.taskmanagerlight.api.goods.food.FoodForm;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Food;
import br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
public class FoodControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TestEntityManager em;
	
	private Food FOOD;
	
	private static String BASE_URI = "/api/food/";
	
	@BeforeEach
	void setup() {
		FOOD = new Food("Beans", new BigDecimal("6.99"));
		FOOD.setExpirationDate(LocalDate.parse("2022-05-15", DateTimeFormatter.ISO_DATE));
		em.persist(FOOD);
		
		Food orangeJuice = new Food("Orange Juice", new BigDecimal("12.50"));
		orangeJuice.setExpirationDate(LocalDate.parse("2022-05-01", DateTimeFormatter.ISO_DATE));
		em.persist(orangeJuice);
		
		Food orange = new Food("Orange", new BigDecimal("2"));
		orange.setExpirationDate(LocalDate.parse("2022-01-01", DateTimeFormatter.ISO_DATE));
		em.persist(orange);
	}
	
	@Test
	void shouldReturn200AndAllGoodsList() throws Exception {
		mockMvc.perform(get(BASE_URI))
			.andDo(log())
			.andExpect(status().isOk());
	}
	
	@Test
	void shouldReturn200ToFindFoodById() throws Exception {
		mockMvc.perform(get(BASE_URI + FOOD.getId()))
			.andDo(log())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", equalTo("Beans")));
	}
	
	@Test
	void shouldReturn404WhenFoodIsNotFoundById() throws Exception {
		mockMvc.perform(get(BASE_URI + "5000012"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	void shouldReturn201WhenFoodIsCreated() throws Exception {
		String payload = TaskmanagerTestUtils.toJsonStr(new FoodForm("Pasta", "2.50"));
		mockMvc.perform(
				post(BASE_URI)
					.content(payload)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
	}
	
	@Test
	void shouldReturn200WhenFoodIsUpdated() throws Exception  {
		String payload = TaskmanagerTestUtils.toJsonStr(new FoodForm("Rice", "22", "2021-06-01"));
		
		mockMvc.perform(
				put(BASE_URI + FOOD.getId())
					.content(payload)
					.contentType(MediaType.APPLICATION_JSON))
			.andDo(log())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", equalTo("Rice")))
			.andExpect(jsonPath("$.price", equalTo("22")))
			.andExpect(jsonPath("$.expirationDate", equalTo("2021-06-01")));
	}
	
	@Test
	void shouldReturn200ToFindFoodByName() throws Exception {
		mockMvc.perform(get(BASE_URI+ "search").queryParam("name", "Orange"))
			.andDo(log())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content[0].name", Matchers.containsString("Orange")));
			
	}
}
