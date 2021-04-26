package br.com.bmo.taskmanagerlight.api.shared.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.bmo.taskmanagerlight.api.shared.exceptions.DateCannotBeInThePast;

class GoodsTest {
	
	private static Goods product;
	
	@BeforeEach
	void setup() {
		product = new Food("TV", new BigDecimal("2"), new BigDecimal("5000"));
	}

	
	@Test
	void shouldNotAcceptNegativePrice() {
		assertThrows(Exception.class, () -> {
			product.setPrice(new BigDecimal("-1"));
		});
	}
	
	@Test
	void shouldNotAcceptNegativeQuantity() {
		assertThrows(Exception.class, () -> {
			product.setQuantity(new BigDecimal("-1"));
		});
	}
	
	@Test
	void shoulAcceptOnlyNumericValueAsPrice() {
		assertThrows(Exception.class, () -> {
			product.setPrice(new BigDecimal("abc"));
		});
	}
	
	@Test
	void shouldAccptOnlyNumericValueAsQuatity() {
		assertThrows(Exception.class, () ->{
			product.setQuantity(new BigDecimal("error.test"));
		});
	}

}
