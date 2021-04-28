package br.com.bmo.taskmanagerlight.api.goods.food;

import br.com.bmo.taskmanagerlight.api.goods.GoodsView;

public class FoodView extends GoodsView{

	private String expirationDate;

	public FoodView(String name, String price, String expirationDate) {
		super(name, price);
		this.expirationDate = expirationDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}
	
}
