package br.com.bmo.taskmanagerlight.api.task.status;

public class StatusView {

	private String id;
	private String name;
	
	public StatusView(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public StatusView(Status status) {
		this.id = String.valueOf(status.getId());
		this.name = status.getName();
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

}
