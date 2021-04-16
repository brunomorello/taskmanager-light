package br.com.bmo.taskmanagerlight.api.task.status;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
@Profile(value = {"test", "prod", "dev", "qas"})
public interface StatusRepository extends JpaRepository<Status, Long> {
	List<Status> findAll();
	Optional<Status> findByName(String name);
}
