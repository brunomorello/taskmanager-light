package br.com.bmo.taskmanagerlight.api.goods.product;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import br.com.bmo.taskmanagerlight.api.goods.GoodsForm;
import br.com.bmo.taskmanagerlight.api.goods.GoodsService;
import br.com.bmo.taskmanagerlight.api.goods.GoodsView;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Product;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@Service
public class ProductService implements GoodsService {

	@Autowired
	private ProductRepository repository;
	
	private static final ProductViewFactory viewFactory = new ProductViewFactory();
	
	@Override
	public Goods create(GoodsForm form) {
		Goods product = form.parse();
		return repository.save((Product) product);
	}

	@Override
	public GoodsView update(@Valid GoodsForm form, Long id) {
		Goods product = form.parse();
		product.setId(id);
		return viewFactory.factory(repository.save((Product) product));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public GoodsView getGoodsById(Long id) {
		return repository.findById(id).map(p -> viewFactory.factory(p)).orElseThrow(() -> new ResourceNotFoundException("Cannot find product by " + id));
	}

	@Override
	public Page<ProductView> getAllGoods(Integer pageNum, Integer pageSize, String sortBy) {
		
		Page<ProductView> allGoods = null;
		
		PageRequest paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		Page<Product> pagedResult = repository.findAll(paging);
		
		if (pagedResult.hasContent()) {
			allGoods = pagedResult.map(viewFactory::factory);
		}		
		
		return allGoods;
	}

	public Page<ProductView> queryProductsBy(MultiValueMap<String, String> queryParams, Integer pageNum,
			Integer pageSize, String sortBy) {
		
		PageRequest paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		ProductSpecificationBuilder specBuilder = new ProductSpecificationBuilder();
		Specification<Product> spec = specBuilder.with(queryParams.toSingleValueMap()).build();
		
		Page<Product> allProducts = repository.findAll(spec, paging);
		
		if (allProducts.hasContent())
			return allProducts.map(viewFactory::factory);
		
		return null;
	}

}
