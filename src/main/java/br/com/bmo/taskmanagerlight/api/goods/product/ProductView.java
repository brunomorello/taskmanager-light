package br.com.bmo.taskmanagerlight.api.goods.product;

import br.com.bmo.taskmanagerlight.api.goods.GoodsView;
import br.com.bmo.taskmanagerlight.api.manufacturer.ManufacturerView;

public class ProductView extends GoodsView{

	private ManufacturerView manufacturer;
	
	public ProductView(String id, String name, String price, ManufacturerView manufacturer) {
		super(id, name, price);
		this.manufacturer = manufacturer;
	}

	public ManufacturerView getManufacturer() {
		return manufacturer;
	}
	
}
