package br.com.bmo.taskmanagerlight.api.shared.integration.goods;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.bmo.taskmanagerlight.api.goods.product.ProductForm;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;
import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;
import br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TestEntityManager em;

	private Product SOFA;
	
	private Manufacturer IKEA;

	private static String BASE_URI = "/api/product/";

	@BeforeEach
	void setup() {
		IKEA = new Manufacturer("Ikea");
		em.persist(IKEA);
		SOFA = new Product("Sofa", new BigDecimal("1000.91"), IKEA);
		em.persist(SOFA);
		
		Product table = new Product("Table", new BigDecimal("500"), null);
		em.persist(table);
		
		Product sofa2 = new Product("Sofa", new BigDecimal("999"), null);
		em.persist(sofa2);
	}

	@Test
	void shouldReturn200ToFindAProductById() throws Exception {
		mockMvc.perform(get(BASE_URI + SOFA.getId())).andDo(log()).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(SOFA.getName())))
				.andExpect(jsonPath("$.price", equalTo(SOFA.getPrice().toString())));
	}

	@Test
	void shouldReturn404WhenProductNotFoundById() throws Exception {
		mockMvc.perform(get(BASE_URI + "2123213")).andExpect(status().isNotFound());
	}

	@Test
	void shouldReturn201WhenProductIsCreated() throws Exception {
		Manufacturer manufacturer = new Manufacturer("samsung");
		em.persist(manufacturer);
		
		ProductForm microwave = new ProductForm("Microwave", "490.91", manufacturer);

		String payload = TaskmanagerTestUtils.toJsonStr(microwave);
		mockMvc.perform(
					post(BASE_URI)
						.content(payload)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.header().exists("location"));
	}

	@Test
	void shouldReturn200WhenProductIsUpdated() throws Exception {
		
		String payload = TaskmanagerTestUtils.toJsonStr(new ProductForm("Table", "840.30", IKEA));

		mockMvc.perform(
					put(BASE_URI + SOFA.getId())
						.content(payload)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(log())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price", equalTo("840.30")))
				.andExpect(jsonPath("$.name", equalTo("Table")))
				.andExpect(jsonPath("$.manufacturer.displayName", equalTo("Ikea")));
	}
	
	@Test
	void shouldReturn200ToFindProductByName() throws Exception {
		mockMvc.perform(get(BASE_URI + "/search").queryParam("name", "=Sofa"))
			.andDo(log())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content[0].name", equalTo("Sofa")))
			.andExpect(jsonPath("$.content[1].name", equalTo("Sofa")));
	}
	
	@Test
	void shouldReturn200ToFindProductByNameAndPriceLowerThan() throws Exception {
		mockMvc.perform(get(BASE_URI + "search").queryParam("name", "Sofa").queryParam("price", "<1000"))
			.andDo(log())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content[0].price", equalTo("999")))
			.andExpect(jsonPath("$.content[0].name", equalTo("Sofa")));
	}

}
