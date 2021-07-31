package br.com.bmo.taskmanagerlight.infra.jpa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

public abstract class CustomSpecificationsBuilder<T> {
	
	protected final List<SearchCriteria> clauseList;
	
	public CustomSpecificationsBuilder() {
		clauseList = new ArrayList<>();
	}
	
	public CustomSpecificationsBuilder<T> with(String key, String operation, String value) {
		clauseList.add(new SearchCriteria(key, operation, value));
		return this;
	}
	
	public CustomSpecificationsBuilder<T> with(Map<String, String> queryParams) {
		queryParams.forEach((key, value) -> {
			String operation = "=";
			
			if (value.startsWith(">"))
				operation = ">";
			if (value.startsWith("<"))
				operation = "<";
				
			clauseList.add(new SearchCriteria(key, operation, value.substring(1)));
		});
		return this;
	}
	
	public abstract Specification<T> build();
	

}