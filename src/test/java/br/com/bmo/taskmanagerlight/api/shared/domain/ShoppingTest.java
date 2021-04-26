package br.com.bmo.taskmanagerlight.api.shared.domain;

import static br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils.DESCRIPTION;
import static br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils.DETAILS;

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
	}

}
