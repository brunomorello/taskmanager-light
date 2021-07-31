package br.com.bmo.taskmanagerlight.api.task;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import br.com.bmo.taskmanagerlight.shared.domain.task.Task;

public interface TaskService<DTOInput, DTOView> {

	public Task create(@Valid DTOInput form);
	
	public DTOView update(@Valid DTOInput form,  Long id);
	
	public void delete(Long id);
	
	public DTOView getTaskById(Long id);
	
	public Page<DTOView> getAllTasks(Integer pageNum, Integer pageSize, String sortBy);
}
