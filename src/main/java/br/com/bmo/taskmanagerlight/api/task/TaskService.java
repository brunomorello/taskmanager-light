package br.com.bmo.taskmanagerlight.api.task;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import br.com.bmo.taskmanagerlight.shared.domain.task.Task;

public interface TaskService {

	public Task create(@Valid TaskDTOInput form);
	
	public TaskDTOView update(@Valid TaskDTOInput form,  Long id);
	
	public void delete(Long id);
	
	public TaskDTOView getTaskById(Long id);
	
	public Page<TaskDTOView> getAllTasks(Integer pageNum, Integer pageSize, String sortBy);
}
