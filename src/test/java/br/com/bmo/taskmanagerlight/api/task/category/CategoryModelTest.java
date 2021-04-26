package br.com.bmo.taskmanagerlight.api.task.category;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import br.com.bmo.taskmanagerlight.api.shared.domain.Category;

@ActiveProfiles("test")
public class CategoryModelTest {

	@Test
	public void shouldInitializeACategory() {
		Category category = new Category("test");
		assertNotNull(category);
		assertEquals(category.getName(), "test");
	}
}
