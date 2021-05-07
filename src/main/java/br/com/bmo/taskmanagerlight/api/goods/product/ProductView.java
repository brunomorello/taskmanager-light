package br.com.bmo.taskmanagerlight.api.goods.product;

import br.com.bmo.taskmanagerlight.api.goods.GoodsView;
import br.com.bmo.taskmanagerlight.api.manufacturer.ManufacturerView;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;

public class ProductView extends GoodsView{

	private ManufacturerView manufacturer;
	
	public ProductView(String id, String name, String price, ManufacturerView manufacturer) {
		super(id, name, price);
		this.manufacturer = manufacturer;
	}
	
	public ProductView(Product product) {
		super(product.getId().toString(), product.getName(), product.getPrice().toString());
		if (product.getManufacturer() != null)
			this.manufacturer = new ManufacturerView(product.getManufacturer());
	}

	public ManufacturerView getManufacturer() {
		return manufacturer;
	}
	
}
