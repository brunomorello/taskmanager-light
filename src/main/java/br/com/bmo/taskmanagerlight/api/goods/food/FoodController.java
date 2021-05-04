package br.com.bmo.taskmanagerlight.api.goods.food;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bmo.taskmanagerlight.api.goods.GoodsListView;
import br.com.bmo.taskmanagerlight.api.goods.GoodsView;
import br.com.bmo.taskmanagerlight.shared.domain.goods.Goods;
import br.com.bmo.taskmanagerlight.shared.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/api/food/")
public class FoodController {
	
	@Autowired
	private FoodsService service;

	@GetMapping("{id}")
	public ResponseEntity<GoodsView> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(service.getGoodsById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("name={name}")
	public ResponseEntity<GoodsListView> findByNameLike(@PathVariable String name) {
		// TODO Auto-generated method stub
		return ResponseEntity.notFound().build();
	}

	@GetMapping("test")
	public ResponseEntity<GoodsListView> findByQueryParams() {
		// TODO Auto-generated method stub
		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<GoodsView> update(@Valid @RequestBody FoodForm form, @PathVariable Long id) {
		
		try {
			GoodsView foodUpdated = service.update(form, id);
			return ResponseEntity.ok(foodUpdated);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody FoodForm form, UriComponentsBuilder uriBuilder) {
		
		Goods foodCreated = service.create(form);
		URI uri = uriBuilder.path("/api/food/{id}").buildAndExpand(foodCreated.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<Page<FoodView>> getAllGoods(
								@RequestParam(defaultValue = "0") Integer pageNum,
								@RequestParam(defaultValue = "10") Integer pageSize,
								@RequestParam(defaultValue = "id") String sortBy) {
		return ResponseEntity.ok(service.getAllGoods(pageNum, pageSize, sortBy));
	}
	
}
