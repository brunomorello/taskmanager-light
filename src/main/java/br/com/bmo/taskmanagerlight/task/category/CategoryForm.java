package br.com.bmo.taskmanagerlight.task.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import br.com.bmo.taskmanagerlight.shared.domain.task.Category;

public class CategoryForm {

	@NotBlank @NotEmpty
	private String name;
	
	public CategoryForm(@NotBlank @NotEmpty String name) {
		this.name = name;
	}
	
	public CategoryForm() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category parse() {
		return new Category(getName());
	}
	
}