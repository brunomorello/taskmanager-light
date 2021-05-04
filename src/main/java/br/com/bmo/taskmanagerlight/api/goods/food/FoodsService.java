package br.com.bmo.taskmanagerlight.api.goods.food;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.bmo.taskmanagerlight.api.goods.GoodsForm;
import br.com.bmo.taskmanagerlight.api.goods.GoodsService;
import br.com.bmo.taskmanagerlight.api.goods.GoodsView;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Food;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@Service
public class FoodsService implements GoodsService {
	
	@Autowired
	private FoodRepositoy repository;
	
	private static final FoodViewFactory viewFactory = new FoodViewFactory();

	@Override
	public Goods create(GoodsForm form) {
		Goods food = form.parse();
		return repository.save((Food) food);
	}

	@Override
	public GoodsView update(@Valid GoodsForm form, Long id) {
		Goods food = form.parse();
		food.setId(id);
		return viewFactory.factory(repository.save((Food) food));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public GoodsView getGoodsById(Long id) {
		return repository.findById(id).map(viewFactory::factory).orElseThrow(() -> new ResourceNotFoundException("Cannot find goods by ID"));
	}

	@Override
	public Page<FoodView> getAllGoods(Integer pageNum, Integer pageSize, String sortBy) {
		
		Page<FoodView> allGoods = null;
		
		PageRequest paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		Page<Food> pagedResult = repository.findAll(paging);

		if (pagedResult.hasContent()) {
			allGoods = pagedResult.map(viewFactory::factory);
		}			
		
		return allGoods;
	}

}
