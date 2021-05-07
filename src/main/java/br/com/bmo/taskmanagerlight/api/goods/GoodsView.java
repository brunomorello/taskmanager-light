package br.com.bmo.taskmanagerlight.api.goods;

import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;

public class GoodsView {

	private String id;
	private String name;
	private String price;
	
	public GoodsView(String id, String name, String price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public GoodsView(Goods g) {
		this.id = g.getId().toString();
		this.name = g.getName();
		this.price = g.getPrice().toString();
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
