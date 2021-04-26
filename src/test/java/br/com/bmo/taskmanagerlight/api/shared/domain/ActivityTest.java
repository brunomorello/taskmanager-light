package br.com.bmo.taskmanagerlight.api.shared.domain;

import static br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils.DESCRIPTION;
import static br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils.DETAILS;
import static br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils.STUDY_CATEGORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActivityTest {
	
private static Activity activity;
	
	@BeforeEach
	void setupActivity() {
		activity = new Activity(DESCRIPTION, DETAILS, STUDY_CATEGORY);
	}
	
	@Test
	void shouldHaveACategory() {
		assertEquals(activity.getCategory().getName(), "Study");
	}
	
	@Test
	void shouldCategoryBeUpdated() {
		assertEquals(activity.getCategory().getName(), STUDY_CATEGORY.getName());
		activity.setCategory(new Category("GYM"));
		assertEquals(activity.getCategory().getName(), "GYM");
	}
	
}
