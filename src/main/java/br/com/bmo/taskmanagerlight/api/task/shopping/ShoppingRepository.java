package br.com.bmo.taskmanagerlight.api.task.shopping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanagerlight.shared.domain.task.Shopping;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long>{
}
