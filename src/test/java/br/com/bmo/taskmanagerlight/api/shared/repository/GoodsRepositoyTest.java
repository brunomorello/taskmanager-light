package br.com.bmo.taskmanagerlight.api.shared.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.bmo.taskmanagerlight.api.goods.GoodsRepositoy;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Food;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;
import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;

@DataJpaTest
@ActiveProfiles("test")
class GoodsRepositoyTest {
	
	@Autowired
	private GoodsRepositoy repository;

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
	void shouldCreateAProduct() {
		Manufacturer dell = new Manufacturer("DELL");
		Product notebook = new Product("latitude", dell);
		notebook.setPrice(new BigDecimal("4300.99"));
		repository.save(notebook);
	}

}
