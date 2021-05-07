package br.com.bmo.taskmanagerlight.api.task.category;

import java.util.ArrayList;
import java.util.List;

import br.com.bmo.taskmanagerlight.shared.domain.task.Category;

public class CategoryListView {

	private List<Category> categoryList = new ArrayList<>();

	public CategoryListView(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
	public CategoryListView() {}
	
	public void add(Category category) {
		this.categoryList.add(category);
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}
	
}
