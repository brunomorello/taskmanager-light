package br.com.bmo.taskmanagerlight.api.goods.product;

import br.com.bmo.taskmanagerlight.api.goods.GoodsViewFactory;
import br.com.bmo.taskmanagerlight.api.manufacturer.ManufacturerView;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;
import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;

public class ProductViewFactory implements GoodsViewFactory {

	@Override
	public ProductView factory(Goods goods) {
		Product product = (Product) goods;
		ManufacturerView manufacturerView = null;
		if (product.getManufacturer() != null) {
			Manufacturer manufacturer = product.getManufacturer();
			manufacturerView = new ManufacturerView(
					manufacturer.getId().toString(), 
					manufacturer.getFormalName(),
					manufacturer.getDisplayName(), 
					manufacturer.getAddress(), 
					manufacturer.getStatus().toString());
		}
		return new ProductView(product.getId().toString(), product.getName(), product.getPrice().toString(),
				manufacturerView);
	}

}
