package br.com.bmo.taskmanagerlight.api.task.status;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class StatusModel {

	@Test
	public void shouldInitializeStatus() {
		String statusName = "Test Status";
		Status status = new Status(statusName);
		assertNotNull(status);
		assertEquals(statusName, status.getName());
	}
}
