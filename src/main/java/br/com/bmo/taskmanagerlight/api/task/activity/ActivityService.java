package br.com.bmo.taskmanagerlight.api.task.activity;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanagerlight.api.task.TaskDTOInput;
import br.com.bmo.taskmanagerlight.api.task.TaskDTOView;
import br.com.bmo.taskmanagerlight.api.task.TaskService;
import br.com.bmo.taskmanagerlight.shared.domain.task.Activity;
import br.com.bmo.taskmanagerlight.shared.domain.task.Task;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@Service
public class ActivityService implements TaskService {

	@Autowired
	private ActivityRepository repository;
	
	@Override
	public Task create(@Valid TaskDTOInput form) {
		Activity activity = (Activity) form.parse();
		return repository.save(activity);
	}

	@Override
	public TaskDTOView update(@Valid TaskDTOInput form, Long id) {
		Activity activity = (Activity) form.parse();
		activity.setId(id);
		return new ActivityDTOView(repository.save(activity));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public TaskDTOView getTaskById(Long id) {
		return repository.findById(id).map(activity -> new ActivityDTOView(activity)).orElseThrow(() -> new ResourceNotFoundException("Cannot find Activity by " + id));
	}

	@Override
	public Page<TaskDTOView> getAllTasks(Integer pageNum, Integer pageSize, String sortBy) {
		PageRequest paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		Page<Activity> pagedResult = repository.findAll(paging);
		
		return pagedResult.map(task -> new ActivityDTOView(task));
	}

}
