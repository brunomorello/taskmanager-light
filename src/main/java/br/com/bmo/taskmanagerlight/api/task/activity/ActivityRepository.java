package br.com.bmo.taskmanagerlight.api.task.activity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanagerlight.shared.domain.task.Activity;
import br.com.bmo.taskmanagerlight.shared.domain.task.Category;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity>{

	List<Activity> findByCategoryAndTitle(Category category, String title);
	List<Activity> findByCategory(Category category);
	
	@Query("SELECT a FROM Activity a WHERE a.dueDate >= :d1 AND a.dueDate <= :d2")
	List<Activity> findByBetweenDueDate(LocalDateTime d1, LocalDateTime d2);

}