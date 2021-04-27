package br.com.bmo.taskmanagerlight.shared.domain.task;

import javax.persistence.Entity;

@Entity
public class Activity extends Task {

	private Category category;
	
	public Activity(String title, String details, Category category) {
		super(title, details);
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
