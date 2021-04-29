package br.com.bmo.taskmanagerlight.api.goods;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;

public abstract class GoodsForm {

	@NotBlank @NotNull
	private String name;
	private String price;
	
	public GoodsForm(@NotBlank String name, String price) {
		this.name = name;
		this.price = price;
	}
	
	public GoodsForm() { }

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}
	
	public abstract Goods parse();

}