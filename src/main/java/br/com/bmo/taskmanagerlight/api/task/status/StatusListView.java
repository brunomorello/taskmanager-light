package br.com.bmo.taskmanagerlight.api.task.status;

import java.util.ArrayList;
import java.util.List;

public class StatusListView {

	private List<Status> statusList = new ArrayList<>();
	
	public StatusListView(List<Status> statusList) {
		this.statusList = statusList;
	}

	public StatusListView() {}
	
	public void addStatus(Status status) {
		statusList.add(status);
	}
	
	public List<Status> getStatusList() {
		return statusList;
	}
	
}
