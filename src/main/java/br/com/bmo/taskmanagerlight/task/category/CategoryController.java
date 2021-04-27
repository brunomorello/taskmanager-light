package br.com.bmo.taskmanagerlight.task.category;

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

import br.com.bmo.taskmanagerlight.shared.domain.task.Category;

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
	
	@PostMapping("/")
	public ResponseEntity<?> create(@RequestBody @Valid CategoryForm form, UriComponentsBuilder uriBuilder) {
		String categoryId = categoryService.createCategoryBy(form);
		URI uri = URI.create("/").resolve(categoryId);
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryView> update(@PathVariable String id, @RequestBody @Valid CategoryForm form) {
		CategoryView categoryView = new CategoryView(categoryService.update(id, form));
		return ResponseEntity.ok(categoryView);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		categoryService.delete(id);
		return ResponseEntity.ok().build();
	}
}
