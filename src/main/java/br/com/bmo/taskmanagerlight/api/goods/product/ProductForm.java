package br.com.bmo.taskmanagerlight.api.goods.product;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import br.com.bmo.taskmanagerlight.api.goods.GoodsForm;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;
import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;

public class ProductForm extends GoodsForm {

	private Manufacturer manufacturer;
	
	public ProductForm(@NotBlank String name, String price, Manufacturer manufacturer) {
		super(name, price);
		this.manufacturer = manufacturer;
	}
	
	public ProductForm(@NotBlank String name, String price) {
		super(name, price);
	}
	
	public ProductForm() { }

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	@Override
	public Goods parse() {
		Product product = new Product(getName(), new BigDecimal(getPrice()));
		if (manufacturer != null)
			product.setManufacturer(manufacturer);
		return product;
	}
	
}
