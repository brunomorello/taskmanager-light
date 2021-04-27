package br.com.bmo.taskmanagerlight.api.shared.domain;

import static br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils.DESCRIPTION;
import static br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils.DETAILS;
import static br.com.bmo.taskmanagerlight.shared.util.TaskmanagerTestUtils.STUDY_CATEGORY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.bmo.taskmanagerlight.shared.domain.task.Activity;
import br.com.bmo.taskmanagerlight.shared.domain.task.Status;
import br.com.bmo.taskmanagerlight.shared.exceptions.InvalidStatusTransition;

class TaskTest {
	
	private static Activity activity;
	
	@BeforeEach
	void setupActivity() {
		activity = new Activity(DESCRIPTION, DETAILS, STUDY_CATEGORY);
	}
	
	@Test
	void activityInitialStatusMustBeBacklog() throws InvalidStatusTransition {
		assertEquals(activity.getStatus(), Status.BACKLOG);
	}
	
	@Test
	void shouldStatusChangesFromBacklogToDoing() throws ReflectiveOperationException {
		activity.setStatus(Status.DOING);
		assertEquals(activity.getStatus(), Status.DOING);
	}
	
	@Test
	void shouldStatusChangesFromBacklogToDone() throws InvalidStatusTransition {
		activity.setStatus(Status.DONE);
		assertEquals(activity.getStatus(), Status.DONE);
	}
	
	@Test
	void shouldNotStatusChangesFromBacklogToPending() throws InvalidStatusTransition {
		assertThrows(InvalidStatusTransition.class, () -> {
			activity.setStatus(Status.PENDING);
		});
	}
	
	@Test
	void shouldActivityHaveItsLifecycleCompleted() throws InvalidStatusTransition {
		
		activity.setStatus(Status.DOING);
		assertEquals(activity.getStatus(), Status.DOING);
		
		activity.setStatus(Status.DONE);
		assertEquals(activity.getStatus(), Status.DONE);
	}
	
	@Test
	void shoulDoneActivityChangeToDoing() throws InvalidStatusTransition {
		activity.setStatus(Status.DOING);
		assertEquals(activity.getStatus(), Status.DOING);
		
		activity.setStatus(Status.DONE);
		assertEquals(activity.getStatus(), Status.DONE);
		
		activity.setStatus(Status.DOING);
		assertEquals(activity.getStatus(), Status.DOING);
	}
	
	@Test
	void shouldNotChangeStatusOfDoneActivityToPending() throws InvalidStatusTransition {
		activity.setStatus(Status.DOING);
		assertEquals(activity.getStatus(), Status.DOING);
		
		activity.setStatus(Status.DONE);
		assertEquals(activity.getStatus(), Status.DONE);
		
		assertThrows(InvalidStatusTransition.class, () -> {
			activity.setStatus(Status.PENDING);
		});
	}
	
	@Test
	void shouldNotDueDateIsInThePast() {
		assertThrows(IllegalArgumentException.class, () -> {
			LocalDateTime past = LocalDateTime.now().minusDays(1);
			activity.setDueDate(past);
		});
	}
	
}
