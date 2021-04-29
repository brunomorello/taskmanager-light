package br.com.bmo.taskmanagerlight.api.goods.food;

import br.com.bmo.taskmanagerlight.api.goods.GoodsViewFactory;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Food;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;

public class FoodViewFactory implements GoodsViewFactory {

	@Override
	public FoodView factory(Goods goods) {
		Food food = (Food) goods;
		return new FoodView(food.getName(), food.getPrice().toString(), food.getExpirationDate().toString());
	}
	
}
