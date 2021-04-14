package br.com.bmo.taskmanagerlight.api.task.status;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface StatusRepository extends Repository<Status, Long> {
	List<Status> findAll();
	Optional<Status> findByName(String name);
	void save(Status status);
}
