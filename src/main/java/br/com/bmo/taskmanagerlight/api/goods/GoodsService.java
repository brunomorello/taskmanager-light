package br.com.bmo.taskmanagerlight.api.goods;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanagerlight.api.goods.food.FoodForm;
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
	
	public Goods create(GoodsForm form)  {
		return repository.save(form.parse());
	}
	
	public GoodsView update(@Valid FoodForm form, Long id) {
		Goods goodsUpdated = form.parse();
		goodsUpdated.setId(id);
		goodsUpdated = repository.save(goodsUpdated);
		System.out.println("updated " + goodsUpdated.getName());
		return viewFactory.factory(goodsUpdated);
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
