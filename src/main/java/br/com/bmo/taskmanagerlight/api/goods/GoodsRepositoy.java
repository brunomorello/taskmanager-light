package br.com.bmo.taskmanagerlight.api.goods;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;

@Repository
public interface GoodsRepositoy extends JpaRepository<Goods, Long> {

	Optional<Goods> findByName(String name);
	
	@Query("SELECT g FROM Goods g WHERE g.name LIKE %:name%")
	List<Goods> findByDisplayNameLike(String name);
}
