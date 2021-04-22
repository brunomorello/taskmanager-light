package br.com.bmo.taskmanagerlight.api.task;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import br.com.bmo.taskmanagerlight.api.task.status.Status;

@Entity
public class Shopping extends Task {
	
	public Shopping(String title, String details, Status status, LocalDateTime dueDate) {
		super(title, details, status, dueDate);
	}

}
