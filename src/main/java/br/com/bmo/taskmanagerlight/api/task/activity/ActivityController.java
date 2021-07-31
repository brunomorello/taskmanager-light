package br.com.bmo.taskmanagerlight.api.task.activity;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bmo.taskmanagerlight.api.task.TaskDTOView;
import br.com.bmo.taskmanagerlight.shared.domain.task.Task;

@RequestMapping("/api/task/activity/")
@RestController
public class ActivityController {

	@Autowired
	private ActivityService service;
	
	@GetMapping("{id}")
	public ResponseEntity<TaskDTOView> getById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(service.getTaskById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		} 
	}

	@GetMapping
	public ResponseEntity<Page<ActivityDTOView>> getAllTasks(
			@RequestParam(defaultValue = "0") Integer pageNum, 
			@RequestParam(defaultValue = "10") Integer pageSize, 
			@RequestParam(defaultValue = "id") String sortBy) {
		return ResponseEntity.ok(service.getAllTasks(pageNum, pageSize, sortBy));
	}

	@PostMapping
	public ResponseEntity<ActivityDTOView> create(@Valid @RequestBody ActivityDTOInput form, UriComponentsBuilder uriBuilder) {
		Task activity = service.create(form);
		URI uri = uriBuilder.path("/api/task/activity/{id}").buildAndExpand(activity.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("{id}")
	public ResponseEntity<TaskDTOView> update(@Valid @RequestBody ActivityDTOInput form, @PathVariable Long id) {
		TaskDTOView activityUpdated = service.update(form, id); 
		return ResponseEntity.ok(activityUpdated);
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<TaskDTOView> replaceCategory(@RequestBody Long categoryId, @PathVariable Long id) {
		return null;
	}
	
	@GetMapping("search")
	public ResponseEntity<Page<ActivityDTOView>> findByQueryParams(
									@RequestParam(defaultValue = "0") Integer pageNum,
									@RequestParam(defaultValue = "10") Integer pageSize,
									@RequestParam(defaultValue = "id") String orderBy,
									@RequestParam MultiValueMap<String, String> queryParams) {
		if (!queryParams.isEmpty()) {
			Page<ActivityDTOView> result = service.queryTasksBy(queryParams, pageNum, pageSize, orderBy);
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("category")
	public List<ActivityDTOView> findByCategory(@Param(value = "category") String category) {
		return service.getActivitiesByCategory(category);
	}
	
}
