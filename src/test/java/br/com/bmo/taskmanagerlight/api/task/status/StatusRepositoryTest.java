package br.com.bmo.taskmanagerlight.api.task.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class StatusRepositoryTest {

	@Autowired
	private StatusRepository statusRepository;
	
	@Test
	void shouldFindStatusByName() {
		String name = "Done";
		Optional<Status> statusFound = statusRepository.findByName(name);
		assertNotNull(statusFound.get());
		assertEquals(name, statusFound.get().getName());
	}

}
