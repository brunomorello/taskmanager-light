package br.com.bmo.taskmanagerlight.api.goods.product;

import br.com.bmo.taskmanagerlight.api.goods.GoodsView;

public class ProductView extends GoodsView{

	private String manufacturuer;
	
	public ProductView(String name, String price, String manufacturuer) {
		super(name, price);
		this.manufacturuer = manufacturuer;
	}

	public String getManufacturuer() {
		return manufacturuer;
	}
	
}
