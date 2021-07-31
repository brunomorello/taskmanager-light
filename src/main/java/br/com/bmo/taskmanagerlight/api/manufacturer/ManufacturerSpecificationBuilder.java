package br.com.bmo.taskmanagerlight.api.manufacturer;

import org.springframework.data.jpa.domain.Specification;

import br.com.bmo.taskmanagerlight.infra.jpa.utils.CustomSpecifications;
import br.com.bmo.taskmanagerlight.infra.jpa.utils.CustomSpecificationsBuilder;
import br.com.bmo.taskmanagerlight.shared.domain.manufacturer.Manufacturer;

public class ManufacturerSpecificationBuilder extends CustomSpecificationsBuilder<Manufacturer> {

	@Override
	public Specification<Manufacturer> build() {
		if (clauseList.size() == 0)
			return null;

		Specification<Manufacturer> spec = null;

		for (int i = 0; i < clauseList.size(); i++) {
			if (i == 0)
				spec = Specification.where(new CustomSpecifications<Manufacturer>(clauseList.get(i)));
			spec = spec.and(new CustomSpecifications<Manufacturer>(clauseList.get(i)));
		}

		return spec;
	}

}
