package br.com.bmo.taskmanagerlight.api.task.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanagerlight.api.shared.exceptions.ResourceNotFoundException;

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
	
}
