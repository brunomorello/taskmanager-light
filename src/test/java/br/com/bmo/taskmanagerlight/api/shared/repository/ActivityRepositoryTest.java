package br.com.bmo.taskmanagerlight.api.shared.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.bmo.taskmanagerlight.api.task.activity.ActivityRepository;
import br.com.bmo.taskmanagerlight.api.task.category.CategoryRepository;
import br.com.bmo.taskmanagerlight.shared.domain.task.Activity;
import br.com.bmo.taskmanagerlight.shared.domain.task.Category;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@DataJpaTest
@ActiveProfiles(profiles = { "test", "dev" } )
class ActivityRepositoryTest {

	@Autowired
	private ActivityRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	void shouldCreateAnActivty() {
		Category category = categoryRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("cannot find category"));
		Activity activity = new Activity("Test", "details", category);
		activity.setDueDate(LocalDateTime.parse("2022-05-10T10:00:00", DateTimeFormatter.ISO_DATE_TIME));
		repository.save(activity);
	}

}