package br.com.bmo.taskmanagerlight.api.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@Service
public class GoodsService {

	@Autowired
	private GoodsRepositoy repository;
	
	private GoodsViewFactory viewFactory;
	
	public void setViewFactory(GoodsViewFactory viewFactory) {
		this.viewFactory = viewFactory;
	}
	
	public Goods save(GoodsForm form)  {
		return repository.save(form.parse());
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public GoodsView findGoodsById(Long id) {
		return repository.findById(id).map(viewFactory::factory).orElseThrow(() -> new ResourceNotFoundException("Cannot find goods by ID"));
	}
	
	public GoodsView findGoodsByNameLike(String name) {
		return repository.findByName(name).map(viewFactory::factory).orElseThrow(() -> new ResourceNotFoundException("Cannot find goods by name"));
	}
	
	public GoodsListView listAll() {
		return new GoodsListView(repository.findAll());
	}
	
}
