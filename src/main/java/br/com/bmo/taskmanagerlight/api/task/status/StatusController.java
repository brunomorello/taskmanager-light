package br.com.bmo.taskmanagerlight.api.task.status;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/task/status")
public class StatusController {

	@Autowired
	private StatusService statusService;
	
	@GetMapping("/")
	public ResponseEntity<StatusListView> getAll() {
		return ResponseEntity.ok(statusService.findAll());
	}
	
	@GetMapping("/name={name}")
	public ResponseEntity<StatusView> getByName(@PathVariable String name) {
		try {
			Status status = statusService.findByName(name);
			return ResponseEntity.ok(new StatusView(status));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StatusView> getById(@PathVariable String id) {
		try {
			Status status = statusService.findById(Long.valueOf(id));
			return ResponseEntity.ok(new StatusView(status));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createStatus(@RequestBody @Valid StatusForm form, UriComponentsBuilder uriBuilder) {
		Status createStatus = statusService.createStatus(form);
		
		URI uri = uriBuilder.path("/api/task/status/{id}").buildAndExpand(createStatus.getId()).toUri();
		return ResponseEntity.created(uri).body(createStatus);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StatusView> updateStatus(@PathVariable String id, @RequestBody @Valid StatusForm form) {
		try {
			Status status = statusService.updateStatus(id, form);
			return ResponseEntity.ok(new StatusView(status));
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();			
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStatus(@PathVariable String id) {
		try {
			Status status = statusService.findById(Long.valueOf(id));
			statusService.deleteStatus(status);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
