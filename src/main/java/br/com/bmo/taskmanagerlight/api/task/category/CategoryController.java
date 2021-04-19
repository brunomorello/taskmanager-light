package br.com.bmo.taskmanagerlight.api.task.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryView> getById(@PathVariable String id) {
		try {
			Category category = categoryService.findById(Long.valueOf(id));
			return ResponseEntity.ok(new CategoryView(category));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/name={name}")
	public ResponseEntity<CategoryView> getByName(@PathVariable String name) {
		try {
			Category category = categoryService.findByName(name);
			return ResponseEntity.ok(new CategoryView(category));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
