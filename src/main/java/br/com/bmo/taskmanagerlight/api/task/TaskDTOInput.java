package br.com.bmo.taskmanagerlight.api.task;

import br.com.bmo.taskmanagerlight.shared.domain.task.Task;

public abstract class TaskDTOInput {

	private String id;
	private String title;
	private String status;
	private String details;
	private String dueDate;
	
	public TaskDTOInput(String id, String title, String details, String status, String dueDate) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.details = details;
		this.dueDate = dueDate;
	}

	public TaskDTOInput(String title, String details, String dueDate) {
		this.title = title;
		this.details = details;
		this.dueDate = dueDate;
	}
	
	public TaskDTOInput() {
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

	public String getDueDate() {
		return dueDate;
	}
	
	public String getStatus() {
		return status;
	}

	public abstract Task parse();
	
}
