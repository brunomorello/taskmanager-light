package br.com.bmo.taskmanagerlight.api.goods;

import java.util.ArrayList;
import java.util.List;

import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;

public class GoodsListView {

	private List<Goods> goods = new ArrayList<>();
	
	public GoodsListView(List<Goods> goods) {
		this.goods = goods;
	}

	public List<Goods> getGoods() {
		return goods;
	}
	
}