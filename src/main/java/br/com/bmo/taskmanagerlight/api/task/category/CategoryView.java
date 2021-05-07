package br.com.bmo.taskmanagerlight.api.task.category;

import br.com.bmo.taskmanagerlight.shared.domain.task.Category;

public class CategoryView {

	private String id;
	private String name;
	
	public CategoryView(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CategoryView() {	}
	
	public CategoryView(Category category) {
		this.id = category.getId() + "";
		this.name = category.getName();
	}

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
