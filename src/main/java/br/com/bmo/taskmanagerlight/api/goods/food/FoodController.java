package br.com.bmo.taskmanagerlight.api.goods.food;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bmo.taskmanagerlight.api.goods.GoodsController;
import br.com.bmo.taskmanagerlight.api.goods.GoodsListView;
import br.com.bmo.taskmanagerlight.api.goods.GoodsService;
import br.com.bmo.taskmanagerlight.api.goods.GoodsView;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/api/food/")
public class FoodController implements GoodsController {
	
	@Autowired
	private GoodsService service;

	@GetMapping("{id}")
	@Override
	public ResponseEntity<GoodsView> findById(@PathVariable Long id) {
		try {
			service.setViewFactory(new FoodViewFactory());
			return ResponseEntity.ok(service.findGoodsById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("name={name}")
	@Override
	public ResponseEntity<GoodsListView> findByNameLike(@PathVariable String name) {
		// TODO Auto-generated method stub
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	@Override
	public ResponseEntity<GoodsListView> findByQueryParams() {
		// TODO Auto-generated method stub
		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<GoodsView> update(@Valid FoodsForm form, Long id) {
		// TODO Auto-generated method stub
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody FoodsForm form, BindingResult results, UriComponentsBuilder uriBuilder) {
		
		if (results.hasErrors()) {
			System.out.println(results);
		}
		
		Goods foodCreated = service.save(form);
		URI uri = uriBuilder.path("/api/food/{id}").buildAndExpand(foodCreated.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
