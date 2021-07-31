package br.com.bmo.taskmanagerlight.api.shared.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import br.com.bmo.taskmanagerlight.api.goods.food.FoodRepositoy;
import br.com.bmo.taskmanagerlight.api.goods.food.FoodSpecificationsBuilder;
import br.com.bmo.taskmanagerlight.infra.jpa.utils.CustomSpecifications;
import br.com.bmo.taskmanagerlight.infra.jpa.utils.SearchCriteria;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Food;

@DataJpaTest
@ActiveProfiles("test")
class FoodRepositoyTest {
	
	@Autowired
	private FoodRepositoy repository;
	
	@BeforeEach
	void setup() {
		Food pizza = new Food("pizza", new BigDecimal("51"));
		repository.save(pizza);
		
		Food salad = new Food("salad", new BigDecimal("9.99"));
		repository.save(salad);
		
		Food orangeJuice = new Food("Natural One - Orange Juice", new BigDecimal("12.50"));
		repository.save(orangeJuice);
		
		Food xandoOrangeJuice = new Food("Xando - Orange Juice", new BigDecimal("9.90"));
		repository.save(xandoOrangeJuice);
	}

	@Test
	void shouldCreateAFood() {
		Food food = new Food("pizza", new BigDecimal("50.20"));
		repository.save(food);
		assertNotNull(food);
		assertNotEquals(food.getId(), null);
		assertEquals(food.getName(), "pizza");
		assertEquals(food.getPrice(), new BigDecimal("50.20"));
	}
	
	@Test
	void shouldFindFoodByName() {
		CustomSpecifications<Food> spec = new CustomSpecifications<Food>(new SearchCriteria("name", "=", "pizza"));
		List<Food> result = repository.findAll(spec);
		
		assertEquals(result.get(0).getName(), "pizza");
	}
	
	@Test
	void shouldFindProductByPriceGreatherThan50() {
		FoodSpecificationsBuilder foodSpecBuilder = new FoodSpecificationsBuilder();
		Specification<Food> spec = foodSpecBuilder.with("price", ">", "50").build();
		
		List<Food> result = repository.findAll(spec);
		
		assertEquals(result.get(0).getPrice(), new BigDecimal("51"));
	}
	
	@Test
	void shouldFindOrangeJuiceWithPriceLessThan10() {
		FoodSpecificationsBuilder foodSpecBuilder = new FoodSpecificationsBuilder();
		Specification<Food> spec = foodSpecBuilder.with("name", "=", "Orange")
						.with("price", "<", "10")
						.build();
		List<Food> result = repository.findAll(spec);
		
		assertEquals(result.get(0).getName(), "Xando - Orange Juice");
		assertEquals(result.get(0).getPrice(), new BigDecimal("9.90"));
	}

}
