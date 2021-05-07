package br.com.bmo.taskmanagerlight.task.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import br.com.bmo.taskmanagerlight.shared.domain.task.Category;

public class CategoryForm {

	@NotBlank @NotEmpty
	private String name;
	private String id;
	
	public CategoryForm(@NotBlank @NotEmpty String name) {
		this.name = name;
	}
	
	public CategoryForm(@NotBlank @NotEmpty String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public CategoryForm() {
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public Category parse() {
		return new Category(getName());
	}
	
}
