package br.com.bmo.taskmanagerlight.api.shared.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.bmo.taskmanagerlight.api.goods.food.FoodRepositoy;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Food;

@DataJpaTest
@ActiveProfiles("test")
class FoodRepositoyTest {
	
	@Autowired
	private FoodRepositoy repository;

	@Test
	void shouldCreateAFood() {
		Food food = new Food("pizza", new BigDecimal("50.20"));
		repository.save(food);
		assertNotNull(food);
		assertNotEquals(food.getId(), null);
		assertEquals(food.getName(), "pizza");
		assertEquals(food.getPrice(), new BigDecimal("50.20"));
	}

}
