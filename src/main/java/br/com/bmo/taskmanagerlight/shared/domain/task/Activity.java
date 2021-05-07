package br.com.bmo.taskmanagerlight.shared.domain.task;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Activity extends Task {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = true)
	private Category category;
	
	public Activity(String title, String details, Category category) {
		super(title, details);
		this.category = category;
	}
	
	public Activity() { }

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
