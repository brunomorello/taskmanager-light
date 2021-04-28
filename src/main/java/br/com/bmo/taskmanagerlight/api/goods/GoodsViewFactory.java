package br.com.bmo.taskmanagerlight.api.goods;

import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;

public interface GoodsViewFactory {
	
	public GoodsView factory(Goods goods);

}
