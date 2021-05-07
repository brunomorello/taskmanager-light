package br.com.bmo.taskmanagerlight.api.task.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanagerlight.shared.domain.task.Category;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceAlreadyExists;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category findById(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot find Category"));
	}

	public Category findByName(String name) {
		return categoryRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Cannot find Category"));
	}

	public String createCategoryBy(CategoryForm form) {
		categoryRepository.findByName(form.getName()).ifPresent((statusFound) -> 
			new ResourceAlreadyExists("Category already exists - ".concat(statusFound.getName()))
		);
		Category category = categoryRepository.save(form.parse());
		return category.getId().toString();
	}

	public Category update(String id, CategoryForm form) {
		Category category = categoryRepository.findById(Long.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("Cannot find Category"));
		category.setName(form.getName());
		return categoryRepository.save(category);
	}

	public void delete(String id) {
		categoryRepository.deleteById(Long.valueOf(id));
	}
	
}
