package br.com.bmo.taskmanagerlight.api.task.status;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanagerlight.api.shared.exceptions.ResourceAlreadyExists;
import br.com.bmo.taskmanagerlight.api.shared.exceptions.ResourceNotFoundException;

@Service
@Profile(value = {"test", "dev", "qas", "prod"})
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;
	
	public StatusListView findAll() {
		StatusListView statusView = new StatusListView();
		statusRepository.findAll().forEach(status -> statusView.addStatus(status));
		return statusView;
	}
	
	public Status findByName(String name) {
		return statusRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Cannot find Status"));
	}
	
	public Status findById(Long id) {
		return statusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot find Status"));
	}
	
	public Status createStatus(StatusForm statusForm) {
		statusRepository.findByName(statusForm.getName()).ifPresent(statusFound -> {
			new ResourceAlreadyExists("Status already exists - ".concat(statusFound.getName()));
		});
		return statusRepository.save(statusForm.parse());
	}

	public void deleteStatus(Status status) {
		statusRepository.delete(status);
		
	}

	public Status updateStatus(String id, @Valid StatusForm form) {
		Status status = statusRepository.findById(Long.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("Cannot find Status"));
		status.setName(form.getName());
		statusRepository.save(status);
		return status;
	}
}
