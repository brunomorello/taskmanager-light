package br.com.bmo.taskmanagerlight.api.goods;

public class GoodsView {

	private String id;
	private String name;
	private String price;
	
	public GoodsView(String id, String name, String price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public String getPrice() {
		return price;
	}
	public String getId() {
		return id;
	}
}
