package br.com.bmo.taskmanagerlight.api.goods.food;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;

import br.com.bmo.taskmanagerlight.api.goods.GoodsForm;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Food;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;

public class FoodsForm extends GoodsForm {
	
	private String expirationDate;

	public FoodsForm(@NotBlank String name, String price) {
		super(name, price);
	}
	
	public FoodsForm(@NotBlank String name, String price, String expirationDate) {
		super(name, price);
		this.expirationDate = expirationDate;
	}
	
	public FoodsForm() { }

	@Override
	public Goods parse() {
		Food food = new Food(getName(), new BigDecimal(getPrice()));
		if (expirationDate != null)
			food.setExpirationDate(LocalDate.parse(expirationDate, DateTimeFormatter.ISO_DATE));
		return food;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

}