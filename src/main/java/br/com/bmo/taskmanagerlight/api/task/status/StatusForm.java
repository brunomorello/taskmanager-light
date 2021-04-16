package br.com.bmo.taskmanagerlight.api.task.status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StatusForm {
	
	@NotBlank @NotNull
	private String name;

	public StatusForm(String name) {
		this.name = name;
	}
	
	public StatusForm() {
	}

	public String getName() {
		return name;
	}
	
	public Status parse() {
		return new Status(this.name);
	}
	
}
