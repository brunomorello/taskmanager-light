package br.com.bmo.taskmanagerlight.api.goods;

public class GoodsView {

	private String name;
	private String price;
	
	public GoodsView(String name, String price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public String getPrice() {
		return price;
	}
}
