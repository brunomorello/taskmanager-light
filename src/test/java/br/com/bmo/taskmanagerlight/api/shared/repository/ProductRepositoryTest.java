package br.com.bmo.taskmanagerlight.api.shared.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import br.com.bmo.taskmanagerlight.api.goods.product.ProductRepository;
import br.com.bmo.taskmanagerlight.api.goods.product.ProductSpecificationBuilder;
import br.com.bmo.taskmanagerlight.api.manufacturer.ManufacturerRepository;
import br.com.bmo.taskmanagerlight.infra.jpa.utils.CustomSpecifications;
import br.com.bmo.taskmanagerlight.infra.jpa.utils.SearchCriteria;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;
import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;

@DataJpaTest
@Transactional
@ActiveProfiles("test")
class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository repository;
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@BeforeEach
	public void setup() {
		Product notebook = new Product("t450", new BigDecimal("111.59"), null);
		repository.save(notebook);
		
		Product notebook2 = new Product("t450", new BigDecimal("1500"), null);
		repository.save(notebook2);
		
		Manufacturer lg = new Manufacturer("LG");
		manufacturerRepository.save(lg);
		
		Product tv = new Product("tv lg",  new BigDecimal("2999"), lg);
		repository.save(tv);
	}

	@Test
	void shouldCreateAProduct() {
		Manufacturer dell = new Manufacturer("DELL");
		Product notebook = new Product("latitude", new BigDecimal("4300.99"), dell);
		repository.save(notebook);
	}
	
	@Test
	void shouldFindProductsByName() {
		
		CustomSpecifications<Product> spec = new CustomSpecifications<Product>(new SearchCriteria("name", "=", "t450"));
		List<Product> result = repository.findAll(spec);
		
		assertEquals(result.get(0).getName(), "t450");
	}
	
	@Test
	void shouldFindProductsWithPriceGreaterThan2500() {
		CustomSpecifications<Product> spec = new CustomSpecifications<Product>(new SearchCriteria("price", ">", "2500"));
		List<Product> result = repository.findAll(spec);
		
		assertEquals(result.get(0).getPrice(), new BigDecimal("2999"));
	}
	
	@Test
	void shouldFindProductByNameLikeAndPriceLessThan1000_UsingPureSpec() {
		CustomSpecifications<Product> specName = new CustomSpecifications<Product>(new SearchCriteria("name", "=", "t450"));
		CustomSpecifications<Product> specPrice = new CustomSpecifications<Product>(new SearchCriteria("price", "<", "1000"));
		
		Specification<Product> spec = Specification.where(specName).and(specPrice);
		
		List<Product> result = repository.findAll(spec);
		
		assertEquals(result.get(0).getName(), "t450");
		assertEquals(result.get(0).getPrice(), new BigDecimal("111.59"));
	}
	
	@Test
	void shouldFindProductByNameLikeAndPriceLessThan1000_UsingSpecBuilder() {
		ProductSpecificationBuilder prodSpecBuilder = new ProductSpecificationBuilder();
		
		Specification<Product> spec = prodSpecBuilder.with("name", "=", "t450")
			.with("price", "<", "1000")
			.build();
		
		List<Product> result = repository.findAll(spec);
		
		assertEquals(result.get(0).getName(), "t450");
		assertEquals(result.get(0).getPrice(), new BigDecimal("111.59"));
	}
	
	@Test
	void shouldFindProductsByPriceGreaterThan1000_UsingSpecBuilder() {
		ProductSpecificationBuilder prodSpecBuilder = new ProductSpecificationBuilder();
		
		Specification<Product> spec = prodSpecBuilder.with("price", ">", "1000").build();
		
		List<Product> result = repository.findAll(spec);
		result.forEach(System.out::println);
		assertEquals(result.size(), 2);
	}

}
