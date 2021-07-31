package br.com.bmo.taskmanagerlight.api.task.activity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import br.com.bmo.taskmanagerlight.api.task.TaskService;
import br.com.bmo.taskmanagerlight.api.task.category.CategoryService;
import br.com.bmo.taskmanagerlight.shared.domain.task.Activity;
import br.com.bmo.taskmanagerlight.shared.domain.task.Category;
import br.com.bmo.taskmanagerlight.shared.domain.task.Task;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@Service
public class ActivityService implements TaskService<ActivityDTOInput, ActivityDTOView> {

	@Autowired
	private ActivityRepository repository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public Task create(@Valid ActivityDTOInput form) {
		Activity activity = (Activity) form.parse();
		return repository.save(activity);
	}

	@Override
	public ActivityDTOView update(@Valid ActivityDTOInput form, Long id) {
		Activity activity = (Activity) form.parse();
		activity.setId(id);
		return new ActivityDTOView(repository.save(activity));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public ActivityDTOView getTaskById(Long id) {
		return repository.findById(id).map(activity -> new ActivityDTOView(activity)).orElseThrow(() -> new ResourceNotFoundException("Cannot find Activity by " + id));
	}

	@Override
	public Page<ActivityDTOView> getAllTasks(Integer pageNum, Integer pageSize, String sortBy) {
		PageRequest paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		Page<Activity> pagedResult = repository.findAll(paging);
		
		return pagedResult.map(task -> new ActivityDTOView(task));
	}

	public Page<ActivityDTOView> queryTasksBy(MultiValueMap<String, String> queryParams, Integer pageNum, Integer pageSize,
			String orderBy) {
		PageRequest paging = PageRequest.of(pageNum, pageSize, Sort.by(orderBy));
		Specification<Activity> spec = new ActivitySpecificationBuilder().with(queryParams.toSingleValueMap()).build();
		
		Page<Activity> result = repository.findAll(spec, paging);
		
		if (result.hasContent())
			return result.map(ActivityDTOView::new);
		return null;
	}
	
	public List<ActivityDTOView> getActivitiesByCategory(String categoryName) {
		Category category = categoryService.findByName(categoryName);
		List<Activity> result = repository.findByCategory(category);
		
		if (!result.isEmpty()) {
			List<ActivityDTOView> activities = new ArrayList<>();
			result.forEach(a -> activities.add(new ActivityDTOView(a)));
			return activities;
		}
		return null;
	}
	
	public List<ActivityDTOView> getActivitiesByDueDatePeriod(LocalDateTime d1, LocalDateTime d2) {
		List<ActivityDTOView> activities = new ArrayList<>();
		List<Activity> result = repository.findByBetweenDueDate(d1, d2);
		result.forEach(a -> activities.add(new ActivityDTOView(a)));
		
		return activities;
	}

}
