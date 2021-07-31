package br.com.bmo.taskmanagerlight.api.goods.food;

import org.springframework.data.jpa.domain.Specification;

import br.com.bmo.taskmanagerlight.infra.jpa.utils.CustomSpecifications;
import br.com.bmo.taskmanagerlight.infra.jpa.utils.CustomSpecificationsBuilder;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Food;

public class FoodSpecificationsBuilder extends CustomSpecificationsBuilder<Food> {

	@Override
	public Specification<Food> build() {
		if (clauseList.size() == 0)
			return null;

		Specification<Food> spec = null;

		for (int i = 0; i < clauseList.size(); i++) {
			if (i == 0)
				spec = Specification.where(new CustomSpecifications<Food>(clauseList.get(i)));
			spec = spec.and(new CustomSpecifications<Food>(clauseList.get(i)));
		}

		return spec;
	}

}
