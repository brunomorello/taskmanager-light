package br.com.bmo.taskmanagerlight.api.task.status;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task/status")
public class StatusController {

	@Autowired
	private StatusService statusService;
	
	@GetMapping("/")
	public ResponseEntity<List<Status>> getAll() {
		return ResponseEntity.ok(statusService.findAll());
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Status> getByName(@PathVariable String name) {
		try {
			Status status = statusService.findByName(name);		
			return ResponseEntity.ok(status);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createStatus(@RequestBody @Valid StatusForm form) {
		return ResponseEntity.ok().build();
	}
}
