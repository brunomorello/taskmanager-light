package br.com.bmo.taskmanagerlight.api.task.shopping;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

@RequestMapping("/api/task/shopping/")
@RestController
public class ShoppingController {
	
	@Autowired
	private ShoppingService service;
	
	@GetMapping("{id}")
	public ResponseEntity<TaskDTOView> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getTaskById(id));
	}

	@GetMapping
	public ResponseEntity<Page<TaskDTOView>> getAllTasks(
			@RequestParam(defaultValue = "0") Integer pageNum, 
			@RequestParam(defaultValue = "10") Integer pageSize, 
			@RequestParam(defaultValue = "id") String sortBy) {
		return ResponseEntity.ok(service.getAllTasks(pageNum, pageSize, sortBy));
	}

	@PostMapping
	public ResponseEntity<ShoppingDTOView> create(@Valid @RequestBody ShoppingDTOInput form, UriComponentsBuilder uriBuilder) {
		Task activity = service.create(form);
		URI uri = uriBuilder.path("/api/task/shopping/{id}").buildAndExpand(activity.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("{id}")
	public ResponseEntity<TaskDTOView> update(@Valid @RequestBody ShoppingDTOInput form, @PathVariable Long id) {
		TaskDTOView activityUpdated = service.update(form, id); 
		return ResponseEntity.ok(activityUpdated);
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<TaskDTOView> replaceCategory(@RequestBody Long categoryId, @PathVariable Long id) {
		return null;
	}

}
