package br.com.bmo.taskmanagerlight.api.task.activity;

import java.time.format.DateTimeFormatter;

import br.com.bmo.taskmanagerlight.api.task.TaskDTOView;
import br.com.bmo.taskmanagerlight.shared.domain.task.Activity;
import br.com.bmo.taskmanagerlight.task.category.CategoryView;

public class ActivityDTOView extends TaskDTOView {
	
	private CategoryView category;

	public ActivityDTOView(String id, String title, String details, String status, String createdOn, String updatedOn,
			String dueDate, CategoryView category) {
		super(id, title, details, status, createdOn, updatedOn, dueDate);
		this.category = category;
	}

	public ActivityDTOView(String title, String details, String status, String createdOn, String updatedOn,
			String dueDate, CategoryView category) {
		super(title, details, status, createdOn, updatedOn, dueDate);
		this.category = category;
	}
	
	public ActivityDTOView(Activity activity) {
		super(activity.getId().toString(), 
				activity.getTitle(), 
				activity.getDetails(), 
				activity.getStatus().toString(),
				activity.getCreatedOn().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), 
				activity.getUpdatedOn().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
				activity.getDueDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		if (activity.getCategory() != null) {
			System.out.println(activity.getCategory().getId() + "-" + activity.getCategory().getName());
			this.category = new CategoryView(activity.getCategory());
		}
	}

	public CategoryView getCategory() {
		return category;
	}
	
}
