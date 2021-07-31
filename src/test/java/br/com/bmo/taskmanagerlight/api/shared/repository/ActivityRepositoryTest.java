package br.com.bmo.taskmanagerlight.api.shared.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import br.com.bmo.taskmanagerlight.api.task.activity.ActivityRepository;
import br.com.bmo.taskmanagerlight.api.task.activity.ActivitySpecificationBuilder;
import br.com.bmo.taskmanagerlight.api.task.category.CategoryRepository;
import br.com.bmo.taskmanagerlight.shared.domain.task.Activity;
import br.com.bmo.taskmanagerlight.shared.domain.task.Category;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@DataJpaTest
@ActiveProfiles("test")
class ActivityRepositoryTest {

	@Autowired
	private ActivityRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@BeforeEach
	void setup() {
		Category studyCategory = categoryRepository.findByName("Study").get();
		Activity at1 = new Activity("Test1", "", studyCategory);
		at1.setDueDate(LocalDateTime.parse("2021-05-20T10:00:00", DateTimeFormatter.ISO_DATE_TIME));
		repository.save(at1);
		
		Category cat2 = new Category("Hosekeeping");
		categoryRepository.save(cat2);
		Activity at2 = new Activity("hello world", "hiya", cat2);
		repository.save(at2);
		
		Activity at3 = new Activity("Test study", null, studyCategory);
		repository.save(at3);
	}
	
	@Test
	void shouldCreateAnActivty() {
		Category category = categoryRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("cannot find category"));
		Activity activity = new Activity("Test", "details", category);
		activity.setDueDate(LocalDateTime.parse("2022-05-10T10:00:00", DateTimeFormatter.ISO_DATE_TIME));
		repository.save(activity);
	}
	
	@Test
	void shouldFindActivityUsingSpec() {
		Specification<Activity> spec = new ActivitySpecificationBuilder().with("title", "LIKE", "Test").build();
		List<Activity> result = repository.findAll(spec);
		
		assertEquals(result.get(0).getTitle(), "Test1");
	}
	
	@Test
	void shouldFindActivityByCategoryAndTitle() {
		Category studyCategory = categoryRepository.findByName("Study").get();
		List<Activity> result = repository.findByCategoryAndTitle(studyCategory, "Test1");
		
		assertEquals(result.get(0).getTitle(), "Test1");
		assertEquals(result.get(0).getCategory().getName(), "Study");
		assertNotEquals(result.size(), 0);
	}

	@Test
	void shouldFindActivityByDueDate() {
		LocalDateTime d1 = LocalDateTime.parse("2021-05-01T00:00:00");
		LocalDateTime d2 = LocalDateTime.parse("2021-06-01T00:00:00");
		List<Activity> result = repository.findByBetweenDueDate(d1, d2);
		
		assertNotEquals(result.size(), 0);
		assertEquals(result.get(0).getTitle(), "Test1");
	}
	
}