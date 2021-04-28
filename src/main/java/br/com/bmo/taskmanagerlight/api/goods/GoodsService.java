package br.com.bmo.taskmanagerlight.api.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@Service
public class GoodsService {

	@Autowired
	private GoodsRepositoy repository;
	@Autowired
	private GoodsViewFactory mapper;
	
	public void save(GoodsFom form) {
		repository.save(form.parse());
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public GoodsView findFoodById(Long id) {
		return repository.findById(id).map(mapper::factory).orElseThrow(() -> new ResourceNotFoundException("Cannot find goods by ID"));
	}
	
	public GoodsView findFoodByNameLike(String name) {
		return repository.findByName(name).map(mapper::factory).orElseThrow(() -> new ResourceNotFoundException("Cannot find goods by name"));
	}
	
	public GoodsListView listAll() {
		return new GoodsListView(repository.findAll());
	}
	
}
