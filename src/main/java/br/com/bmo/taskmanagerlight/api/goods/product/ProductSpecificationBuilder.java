package br.com.bmo.taskmanagerlight.api.goods.product;

import org.springframework.data.jpa.domain.Specification;

import br.com.bmo.taskmanagerlight.infra.jpa.utils.CustomSpecifications;
import br.com.bmo.taskmanagerlight.infra.jpa.utils.CustomSpecificationsBuilder;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;

public class ProductSpecificationBuilder extends CustomSpecificationsBuilder<Product> {

	@Override
	public Specification<Product> build() {
		if (clauseList.size() == 0)
			return null;

		Specification<Product> spec = null;

		for (int i = 0; i < clauseList.size(); i++) {
			if (i == 0)
				spec = Specification.where(new CustomSpecifications<Product>(clauseList.get(i)));
			spec = spec.and(new CustomSpecifications<Product>(clauseList.get(i)));
		}

		return spec;
	}

}
