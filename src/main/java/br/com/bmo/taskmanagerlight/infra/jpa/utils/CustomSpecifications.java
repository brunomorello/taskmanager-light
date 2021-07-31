package br.com.bmo.taskmanagerlight.infra.jpa.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class CustomSpecifications<T> implements Specification<T> {
	
	private static final long serialVersionUID = 1L;
	private SearchCriteria searchCriteria;

	public CustomSpecifications(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (searchCriteria.getOperation().equalsIgnoreCase(">"))
			return criteriaBuilder.greaterThanOrEqualTo(root.<String> get(searchCriteria.getKey()), searchCriteria.getValue());
		if (searchCriteria.getOperation().equalsIgnoreCase("<"))
			return criteriaBuilder.lessThanOrEqualTo(root.<String> get(searchCriteria.getKey()), searchCriteria.getValue());
		if (searchCriteria.getOperation().equalsIgnoreCase("="))
			return criteriaBuilder.equal(root.<String> get(searchCriteria.getKey()), searchCriteria.getValue());
		if (searchCriteria.getOperation().equalsIgnoreCase("LIKE"))
			return criteriaBuilder.like(root.<String> get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
		return null;
	}

}