package br.com.bmo.taskmanagerlight.api.goods.product;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import br.com.bmo.taskmanagerlight.api.goods.GoodsFom;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;
import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;

public class ProductForm extends GoodsFom {

	private String manufacturer;
	
	public ProductForm(@NotBlank String name, String price, String manufacturer) {
		super(name, price);
		this.manufacturer = manufacturer;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	@Override
	public Goods parse() {
		Product product = new Product(getName(), new BigDecimal(getPrice()));
		product.setManufacturer(new Manufacturer(manufacturer));
		return product;
	}
	
}
