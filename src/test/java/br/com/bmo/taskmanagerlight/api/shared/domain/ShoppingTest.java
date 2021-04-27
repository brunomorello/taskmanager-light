package br.com.bmo.taskmanagerlight.api.shared.domain;

import static br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils.DESCRIPTION;
import static br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils.DETAILS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.bmo.taskmanagerlight.shared.domain.goods.Food;
import br.com.bmo.taskmanagerlight.shared.domain.task.Shopping;

class ShoppingTest {
	
	private static Shopping activity;
	
	@BeforeEach
	void setupActivity() {
		activity = new Shopping(DESCRIPTION, DETAILS);
		activity.addProduct(new Food("Bread", new BigDecimal("4.5")));
	}

	@Test
	void shouldAddAProduct() {
		assertEquals(activity.getListSize(), 1);
	}
	
	@Test
	void shouldGetSpecificProductQuantity() {
		Food bread = new Food("Bread", new BigDecimal("4.5"));
		Food soda = new Food("Soda", new BigDecimal("2"));
		activity.addProduct(bread);
		activity.addProduct(soda);
		assertEquals(activity.getQuantityOf(bread), 2L);
	}
	
	@Test
	void shouldEmptyProductsList() {
		activity.clearList();
		assertEquals(activity.getListSize(), 0);
	}

}
