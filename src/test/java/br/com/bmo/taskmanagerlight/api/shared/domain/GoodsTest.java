package br.com.bmo.taskmanagerlight.api.shared.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.bmo.taskmanagerlight.api.shared.domain.goods.Food;
import br.com.bmo.taskmanagerlight.api.shared.domain.goods.Goods;

class GoodsTest {
	
	private static Goods product;
	
	@BeforeEach
	void setup() {
		product = new Food("TV", new BigDecimal("5000"));
	}

	@Test
	void shouldNotAcceptNegativePrice() {
		assertThrows(Exception.class, () -> {
			product.setPrice(new BigDecimal("-1"));
		});
	}
	
	@Test
	void shoulAcceptOnlyNumericValueAsPrice() {
		assertThrows(Exception.class, () -> {
			product.setPrice(new BigDecimal("abc"));
		});
	}
	
}
