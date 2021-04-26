package br.com.bmo.taskmanagerlight.api.task.category;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bmo.taskmanagerlight.api.shared.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	void shouldFindACategoryByName() {
		String name = "Cook";
		Optional<Category> categoryOpt = categoryRepository.findByName(name);
		Category category = categoryOpt.get();
		assertNotNull(category);
		assertEquals(category.getName(), name);
	}
	
	@Test
	void shouldFindCategoryById() {
		Long id = 1L;
		Optional<Category> categoryOpt = categoryRepository.findById(id);
		Category category = categoryOpt.get();
		assertNotNull(category);
		assertEquals(category.getId(), id);
	}
	
	@Test
	void shouldReturnAListOfCategories() {
		List<Category> allCategories = categoryRepository.findAll();
		assertNotEquals(allCategories.size(), 0);
	}
}
