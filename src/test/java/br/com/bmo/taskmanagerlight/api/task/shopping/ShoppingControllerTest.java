package br.com.bmo.taskmanagerlight.api.task.shopping;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

import br.com.bmo.taskmanagerlight.api.goods.product.ProductView;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;
import br.com.bmo.taskmanagerlight.shared.domain.task.Shopping;
import br.com.bmo.taskmanagerlight.shared.domain.task.Status;
import br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
class ShoppingControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TestEntityManager em;
	
	private Shopping TOBUY;
	
	private static String BASE_URI = "/api/task/shopping/";

	@BeforeEach
	void setup() {
		TOBUY = new Shopping("Buy sneakers", "nike");
		em.persist(TOBUY);
	}
	
	@Test
	void souldReturn200ToFindAShoppingTaskByID() throws Exception {
		mockMvc.perform(get(BASE_URI + TOBUY.getId()))
			.andDo(log())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title", equalTo(TOBUY.getTitle())));
	}
	
	@Test
	void shouldReturn201ToCreateAShoppingTask() throws Exception {
		List<ProductView> productList = new ArrayList<>();
		Product p1 = new Product("apple", new BigDecimal("1.10"));
		em.persist(p1);
		productList.add(new ProductView(p1));
		
		Product p2 = new Product("orange juice", new BigDecimal("11.00"));
		em.persist(p2);
		productList.add(new ProductView(p2));		
		
		ShoppingDTOInput shoppingTask = new ShoppingDTOInput("Buy items to breakfast", "organic fruits", "2021-05-17T12:00:00", productList);
		String payload = TaskmanagerTestUtils.toJsonStr(shoppingTask);	
		
		mockMvc.perform(
				post(BASE_URI)
					.content(payload)
					.contentType(MediaType.APPLICATION_JSON))
			.andDo(log())
			.andExpect(status().isCreated());
	}
	
	@Test
	void shouldReturn200ToUpdateAShoppingTask() throws Exception {
		List<ProductView> productList = new ArrayList<>();
		Product p1 = new Product("apple", new BigDecimal("1.10"));
		em.persist(p1);
		productList.add(new ProductView(p1));
		
		ShoppingDTOInput shoppingTask = new ShoppingDTOInput(TOBUY.getId().toString(), "NEW TITLE", ".", Status.DOING.toString(), "2021-05-17T12:00:00", productList);
		String payload = TaskmanagerTestUtils.toJsonStr(shoppingTask);
		
		mockMvc.perform(
				put(BASE_URI + TOBUY.getId())
					.content(payload)
					.contentType(MediaType.APPLICATION_JSON))
			.andDo(log())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title", equalTo("NEW TITLE")))
			.andExpect(jsonPath("$.details", equalTo(".")))
			.andExpect(jsonPath("$.dueDate", equalTo("2021-05-17T12:00:00")));
		
	}
	
	@Test
	void shouldReturn200AndAShoppingTaskList() throws Exception {
		mockMvc.perform(get(BASE_URI))
			.andDo(log())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content[0].title", equalTo(TOBUY.getTitle())));
	}

}
