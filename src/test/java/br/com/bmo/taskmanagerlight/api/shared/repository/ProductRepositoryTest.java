package br.com.bmo.taskmanagerlight.api.shared.repository;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.bmo.taskmanagerlight.api.goods.product.ProductRepository;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;
import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;

@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository repository; 

	@Test
	void shouldCreateAProduct() {
		Manufacturer dell = new Manufacturer("DELL");
		Product notebook = new Product("latitude", new BigDecimal("4300.99"), dell);
		repository.save(notebook);
	}

}
