package br.com.bmo.taskmanagerlight.api.task.activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.bmo.taskmanagerlight.api.task.TaskDTOInput;
import br.com.bmo.taskmanagerlight.shared.domain.task.Activity;
import br.com.bmo.taskmanagerlight.shared.domain.task.Status;
import br.com.bmo.taskmanagerlight.shared.domain.task.Task;
import br.com.bmo.taskmanagerlight.shared.exceptions.InvalidStatusTransition;
import br.com.bmo.taskmanagerlight.task.category.CategoryForm;

public class ActivityDTOInput extends TaskDTOInput {
	
	private CategoryForm category;

	public ActivityDTOInput(String title, String details, String dueDate, CategoryForm category) {
		super(title, details, dueDate);
		this.category = category;
	}

	public ActivityDTOInput(String id, String title, String details, String status, String dueDate, CategoryForm category) {
		super(id, title, details, status, dueDate);
		this.category = category;
	}
	
	public ActivityDTOInput() {
	}

	public CategoryForm getCategory() {
		return category;
	}

	@Override
	public Task parse() {
		Activity activity = new Activity(getTitle(), getDetails(), category.parse());
		if (getStatus() != null) {
			try {
				activity.setStatus(Status.valueOf(getStatus()));
			} catch (InvalidStatusTransition e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		activity.setDueDate(LocalDateTime.parse(getDueDate(), DateTimeFormatter.ISO_DATE_TIME));
		return activity;
	}

}
