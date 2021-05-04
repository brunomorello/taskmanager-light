package br.com.bmo.taskmanagerlight.api.goods.food;

import br.com.bmo.taskmanagerlight.api.goods.GoodsView;

public class FoodView extends GoodsView{

	private String expirationDate;

	public FoodView(String id, String name, String price, String expirationDate) {
		super(id, name, price);
		this.expirationDate = expirationDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}
	
}
