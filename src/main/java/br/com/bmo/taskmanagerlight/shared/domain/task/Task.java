package br.com.bmo.taskmanagerlight.shared.domain.task;
import java.time.LocalDateTime;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.com.bmo.taskmanagerlight.shared.exceptions.DateCannotBeInThePast;
import br.com.bmo.taskmanagerlight.shared.exceptions.InvalidStatusTransition;

@Entity
@Table(name = "tasks")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "task_type")
public abstract class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String details;
	@Enumerated(EnumType.STRING)
	private Status status;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	private LocalDateTime dueDate;
	
	public Task(String title, String details) {
		this.title = title;
		this.details = details;
		this.status = Status.BACKLOG;
		
		if (getId() == null) {
			setCreatedOn(LocalDateTime.now());
		}
		setUpdatedOn(LocalDateTime.now());
	}
	
	public Task() { }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) throws InvalidStatusTransition {
		this.status = getStatus().isValidTransitionTo(status);
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		if (dueDate.isBefore(LocalDateTime.now()))
			throw new DateCannotBeInThePast("Due Date must not be in past");
		this.dueDate = dueDate;
	}
}