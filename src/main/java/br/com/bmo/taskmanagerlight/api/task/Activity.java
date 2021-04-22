package br.com.bmo.taskmanagerlight.api.task;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import br.com.bmo.taskmanagerlight.api.task.category.Category;
import br.com.bmo.taskmanagerlight.api.task.status.Status;

@Entity
public class Activity extends Task {

	private Category category;
	
	public Activity(String title, String details, Status status, LocalDateTime dueDate, Category category) {
		super(title, details, status, dueDate);
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
