package br.com.bmo.taskmanagerlight.api.shared.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.bmo.taskmanagerlight.api.shared.domain.goods.Food;
import br.com.bmo.taskmanagerlight.api.shared.exceptions.DateCannotBeInThePast;

class FoodTest {

	private static Food food;
	
	@BeforeEach
	void setup() {
		food = new Food("Bread", new BigDecimal("0.70"));
	}

	@Test
	void shouldNotAcceptExpirationDateInThePast() {
		assertThrows(DateCannotBeInThePast.class, () -> {
			food.setExpirationDate(LocalDate.now().minusDays(1));
		});
	}

}
