package br.com.bmo.taskmanagerlight.api.manufacturer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class ManufacturerRepositoryTest {

	@Autowired
	private ManufacturerRepository repository;
	
	@Autowired
	private TestEntityManager EM;
	
	@BeforeEach
	void setup() {
		Manufacturer acme1 = new Manufacturer("Acme 1 S.A.", "AC1", "Av. Paulista");
		Manufacturer acme2 = new Manufacturer("Manufacturer Acme 2 LTDA", "Acme", "Paulista Avenue");
		Manufacturer acme3 = new Manufacturer("Acme LOL");
		
		EM.persist(acme1);
		EM.persist(acme2);
		EM.persist(acme3);
	}
	
	@Test
	void shouldFindManufacturerByDisplayName() {
		Optional<Manufacturer> acme = repository.findByDisplayName("AC1");
		assertEquals(acme.isPresent(), true);
		assertEquals(acme.get().getDisplayName(), "AC1");
	}
	
	@Test
	void shouldFindManufacturerByFormalName() {
		Optional<Manufacturer> acme = repository.findByFormalName("Manufacturer Acme 2 LTDA");
		assertNotNull(acme.get());
		assertEquals(acme.get().getFormalName(), "Manufacturer Acme 2 LTDA");
	}
	
	@Test
	void shouldFindManufacturersByPaulistaAddress() {
		List<Manufacturer> manufactureresFound = repository.findByAddressLike("Paulista");
		manufactureresFound.forEach(manufacturer -> assertEquals(manufacturer.getAddress().contains("Paulista"), true));
	}
	
	@Test
	void shouldUpdateManufacturer() {
		Manufacturer manufacturer = repository.findByDisplayName("AC1").get();
		manufacturer.setDisplayName("Test");
		EM.persist(manufacturer);
		assertEquals(manufacturer.getDisplayName(), "Test");
	}
}
