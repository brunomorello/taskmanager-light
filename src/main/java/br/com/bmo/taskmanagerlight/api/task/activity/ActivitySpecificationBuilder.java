package br.com.bmo.taskmanagerlight.api.task.activity;

import org.springframework.data.jpa.domain.Specification;

import br.com.bmo.taskmanagerlight.infra.jpa.utils.CustomSpecifications;
import br.com.bmo.taskmanagerlight.infra.jpa.utils.CustomSpecificationsBuilder;
import br.com.bmo.taskmanagerlight.shared.domain.task.Activity;

public class ActivitySpecificationBuilder extends CustomSpecificationsBuilder<Activity> {

	@Override
	public Specification<Activity> build() {
		if (clauseList.size() == 0)
			return null;

		Specification<Activity> spec = null;

		for (int i = 0; i < clauseList.size(); i++) {
			if (i == 0)
				spec = Specification.where(new CustomSpecifications<Activity>(clauseList.get(i)));
			spec = spec.and(new CustomSpecifications<Activity>(clauseList.get(i)));
		}

		return spec;
	}

}