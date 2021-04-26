package br.com.bmo.taskmanagerlight.api.shared.domain;

import static br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils.DESCRIPTION;
import static br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils.DETAILS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingTest {
	
	private static Shopping activity;
	
	@BeforeEach
	void setupActivity() {
		activity = new Shopping(DESCRIPTION, DETAILS);
	}

	@Test
	void shouldAddAProduct() {
		activity.addProduct(new Food("Bread", new BigDecimal(2), new BigDecimal("4.5")));
		assertNotEquals(activity.getProducts().size(), 0);
	}
	
	@Test
	void shouldNotAcceptDuplicatedProduct() {
		Food bread1 = new Food("Bread", new BigDecimal(1), new BigDecimal("0.8"));
		Food bread2 = new Food("Bread", new BigDecimal(1), new BigDecimal("0.8"));
		
		activity.addProduct(bread1);
		activity.addProduct(bread2);
		
		assertEquals(activity.getProducts().size(), 1);
	}
	
	@Test
	void shouldEmptyProductsList() {
		Food bread1 = new Food("Bread", new BigDecimal(1), new BigDecimal("0.8"));
		activity.addProduct(bread1);
		activity.clearList();
		assertEquals(activity.getProducts().size(), 0);
	}

}
