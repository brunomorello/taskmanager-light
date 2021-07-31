package br.com.bmo.taskmanagerlight.api.goods.food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanagerlight.shared.domain.goods.Food;

@Repository
public interface FoodRepositoy extends PagingAndSortingRepository<Food, Long>, JpaSpecificationExecutor<Food> {

	@Query("SELECT g FROM Goods g WHERE g.name LIKE %:name%")
	List<Food> findByDisplayNameLike(String name);
}
