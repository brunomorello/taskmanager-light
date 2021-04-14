package br.com.bmo.taskmanagerlight.api.task.status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanagerlight.api.shared.exceptions.ResourceNotFoundException;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;
	
	public List<Status> findAll() {
		return statusRepository.findAll();
	}
	
	public Status findByName(String name) throws ResourceNotFoundException {
		return statusRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Cannot find Status"));
	}
}
