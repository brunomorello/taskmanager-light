package br.com.bmo.taskmanagerlight.api.task;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.bmo.taskmanagerlight.api.task.status.Status;
import br.com.bmo.taskmanagerlight.api.user.User;

public abstract class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String details;
	private Status status;
	private List<User> owners;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	private LocalDateTime dueDate;
	
	public Task(String title, String details, Status status, LocalDateTime dueDate) {
		this.title = title;
		this.details = details;
		this.status = status;
		this.dueDate = dueDate;
		
		if (getId() == null) {
			setCreatedOn(LocalDateTime.now());
		}
		setUpdatedOn(LocalDateTime.now());
	}
	
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
	public void setStatus(Status status) {
		this.status = status;
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
		this.dueDate = dueDate;
	}
	public List<User> getOwners() {
		return owners;
	}
	public void setOwners(List<User> owners) {
		this.owners = owners;
	}
	
	public void assignTo(User user) {
		this.owners.add(user);
	}
	
}