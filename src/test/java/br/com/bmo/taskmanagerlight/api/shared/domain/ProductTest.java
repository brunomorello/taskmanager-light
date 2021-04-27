package br.com.bmo.taskmanagerlight.api.shared.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.bmo.taskmanagerlight.api.shared.domain.goods.Product;
import br.com.bmo.taskmanagerlight.api.shared.domain.manufacturer.Manufacturer;

class ProductTest {

	@Test
	void shouldProductHaveAManufacturerAndPrice() {
		Product notebook = new Product("Notebook");
		Manufacturer dell = new Manufacturer("dell");
		notebook.setManufacturer(dell);
		assertEquals(notebook.getName(), "Notebook");
		assertEquals(notebook.getManufacturer(), dell);
	}

}
