package br.com.bmo.taskmanagerlight.api.task;

import java.time.LocalDateTime;
import java.util.List;

import br.com.bmo.taskmanagerlight.api.task.category.Category;
import br.com.bmo.taskmanagerlight.api.task.status.Status;

public abstract class Task {

	private String title;
	private String details;
	private Status status;
	private Category category;
	private List<User> owner;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime dueDate;
	private List<TaskItem> checklist;
}
