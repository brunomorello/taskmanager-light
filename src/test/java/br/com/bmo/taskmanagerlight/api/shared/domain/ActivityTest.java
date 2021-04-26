package br.com.bmo.taskmanagerlight.api.shared.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.bmo.taskmanagerlight.api.shared.exceptions.InvalidStatusTransition;
import br.com.bmo.taskmanagerlight.api.task.category.Category;

class ActivityTest {
	
	private static final String DESCRIPTION = "Activity Task";
	private static final String DETAILS = "Activity test";
	private static final Category STUDY_CATEGORY = new Category("Study");
	
	@Test
	void activityInitialStatusMustBeBacklog() throws InvalidStatusTransition {
		Activity activity = new Activity(DESCRIPTION, DETAILS, STUDY_CATEGORY);
		assertEquals(activity.getStatus(), Status.BACKLOG);
	}
	
	@Test
	void shouldStatusChangesFromBacklogToDoing() throws ReflectiveOperationException {
		Activity activity = new Activity(DESCRIPTION, DETAILS, STUDY_CATEGORY);
		activity.setStatus(Status.DOING);
		assertEquals(activity.getStatus(), Status.DOING);
	}
	
	@Test
	void shouldStatusChangesFromBacklogToDone() throws InvalidStatusTransition {
		Activity activity = new Activity(DESCRIPTION, DETAILS, STUDY_CATEGORY);
		activity.setStatus(Status.DONE);
		assertEquals(activity.getStatus(), Status.DONE);
	}
	
	@Test
	void shouldNotStatusChangesFromBacklogToPending() throws InvalidStatusTransition {
		Activity activity = new Activity(DESCRIPTION, DETAILS, STUDY_CATEGORY);
		assertThrows(InvalidStatusTransition.class, () -> {
			activity.setStatus(Status.PENDING);
		});
	}

}
