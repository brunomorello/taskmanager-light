package br.com.bmo.taskmanagerlight.api.goods.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

	@Query("SELECT g FROM Goods g WHERE g.name LIKE %:name%")
	List<Product> findByDisplayNameLike(String name);
}
