package br.com.bmo.taskmanagerlight.api.goods.food;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;

import br.com.bmo.taskmanagerlight.api.goods.GoodsForm;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Food;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;

public class FoodForm extends GoodsForm {

	private String expirationDate;
	
	public FoodForm(@NotBlank String name, String price) {
		super(name, price);
	}
	
	public FoodForm(@NotBlank String name, String price, String expirationDate) {
		super(name, price);
		this.expirationDate = expirationDate;
	}
	
	public FoodForm() { }
	
	@Override
	public Goods parse() {
		Food food = new Food(getName(), new BigDecimal(getPrice()));
		if (expirationDate != null)
			food.setExpirationDate(LocalDate.parse(expirationDate, DateTimeFormatter.ISO_DATE));
		return food;
	}
	
	public String getExpirationDate() {
		return this.expirationDate;
	}
	
}