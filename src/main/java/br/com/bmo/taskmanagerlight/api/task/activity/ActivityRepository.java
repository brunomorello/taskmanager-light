package br.com.bmo.taskmanagerlight.api.task.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanagerlight.shared.domain.task.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
}
