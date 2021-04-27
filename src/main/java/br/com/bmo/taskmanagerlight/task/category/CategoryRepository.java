package br.com.bmo.taskmanagerlight.task.category;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanagerlight.shared.domain.task.Category;

@Repository
@Profile(value = { "test", "prod", "qas", "dev" })
public interface CategoryRepository extends JpaRepository<Category, Long>{
	List<Category> findAll();
	Optional<Category> findByName(String name);
}
