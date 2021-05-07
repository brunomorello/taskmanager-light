package br.com.bmo.taskmanagerlight.api.task.shopping;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanagerlight.api.task.TaskDTOInput;
import br.com.bmo.taskmanagerlight.api.task.TaskDTOView;
import br.com.bmo.taskmanagerlight.api.task.TaskService;
import br.com.bmo.taskmanagerlight.shared.domain.task.Shopping;
import br.com.bmo.taskmanagerlight.shared.domain.task.Task;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@Service
public class ShoppingService implements TaskService {
	
	@Autowired
	private ShoppingRepository repository;

	@Override
	public Task create(@Valid TaskDTOInput form) {
		Shopping shoppingTask = (Shopping) form.parse();
		return repository.save(shoppingTask);
	}

	@Override
	public TaskDTOView update(@Valid TaskDTOInput form, Long id) {
		Shopping shoppingTask = (Shopping) form.parse();
		shoppingTask.setId(id);
		return new ShoppingDTOView(repository.save(shoppingTask));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public TaskDTOView getTaskById(Long id) {
		return repository.findById(id).map(task -> new ShoppingDTOView(task)).orElseThrow(() -> new ResourceNotFoundException("Cannot find Shopping Task by " + id));
	}

	@Override
	public Page<TaskDTOView> getAllTasks(Integer pageNum, Integer pageSize, String sortBy) {
		PageRequest paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		Page<Shopping> pagedResult = repository.findAll(paging);
		
		return pagedResult.map(task -> new ShoppingDTOView(task));
	}

}
