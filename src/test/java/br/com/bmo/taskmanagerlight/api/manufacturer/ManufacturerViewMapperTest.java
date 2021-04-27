package br.com.bmo.taskmanagerlight.api.manufacturer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;

public class ManufacturerViewMapperTest {

	private static final Manufacturer SIMPLE_ACME = new Manufacturer("Acme");
	private static final Manufacturer COMPLETE_ACME = new Manufacturer("Acme Corp.", "Acme", "Av Paulista");
	
	private ManufacturerViewMapper mapper = new ManufacturerViewMapper();
	
	@Test
	void shouldConvertSimpleManufacturerToManufacturerViewMapper() {
		ManufacturerView manufacturerView = mapper.map(SIMPLE_ACME);
		
		assertEquals(SIMPLE_ACME.getDisplayName(), manufacturerView.getDisplayName());
	}
	
	@Test
	void shouldConvertCompleteManufacturerToManufacturerViewMapper() {
		ManufacturerView manufacturerView = mapper.map(COMPLETE_ACME);
		
		assertEquals(COMPLETE_ACME.getFormalName(), manufacturerView.getFormalName());
		assertEquals(COMPLETE_ACME.getDisplayName(), manufacturerView.getDisplayName());
		assertEquals(COMPLETE_ACME.getAddress(), manufacturerView.getAddress());
		assertEquals(COMPLETE_ACME.getStatus().toString(), manufacturerView.getStatus());
	}
	
	@Test
	void shouldNewManufacturerHasActiveStatus() {
		ManufacturerView manufacturerView = mapper.map(COMPLETE_ACME);
		assertEquals(manufacturerView.getStatus(), ManufacturerStatus.ACTIVE.toString());
	}
}
