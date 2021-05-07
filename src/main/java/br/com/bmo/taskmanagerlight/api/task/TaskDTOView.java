package br.com.bmo.taskmanagerlight.api.task;

import java.time.format.DateTimeFormatter;

import br.com.bmo.taskmanagerlight.shared.domain.task.Task;

public abstract class TaskDTOView {

	private String id;
	private String title;
	private String details;
	private String status;
	private String createdOn;
	private String updatedOn;
	private String dueDate;
	
	public TaskDTOView(String id, String title, String details, String status, String createdOn, String updatedOn,
			String dueDate) {
		this.id = id;
		this.title = title;
		this.details = details;
		this.status = status;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.dueDate = dueDate;
	}

	public TaskDTOView(String title, String details, String status, String createdOn, String updatedOn, String dueDate) {
		this.title = title;
		this.details = details;
		this.status = status;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.dueDate = dueDate;
	}
	
	public TaskDTOView(Task task) {
		this.id = task.getId().toString();
		this.title = task.getTitle();
		this.details = task.getDetails();
		this.createdOn = task.getCreatedOn().format(DateTimeFormatter.ISO_DATE_TIME);
		this.updatedOn = task.getUpdatedOn().format(DateTimeFormatter.ISO_DATE_TIME);
		this.dueDate = task.getDueDate().format(DateTimeFormatter.ISO_DATE_TIME);
	}
	
	public TaskDTOView() {
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDetails() {
		return details;
	}

	public String getStatus() {
		return status;
	}

	public String getDueDate() {
		return dueDate;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}
	
}
