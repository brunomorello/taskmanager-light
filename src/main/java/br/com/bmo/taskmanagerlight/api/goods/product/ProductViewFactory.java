package br.com.bmo.taskmanagerlight.api.goods.product;

import br.com.bmo.taskmanagerlight.api.goods.GoodsView;
import br.com.bmo.taskmanagerlight.api.goods.GoodsViewFactory;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;

public class ProductViewFactory implements GoodsViewFactory {

	@Override
	public GoodsView factory(Goods goods) {
		Product product = (Product) goods;
		return new ProductView(product.getName(), product.getPrice().toString(), product.getManufacturer().toString());
	}

}
